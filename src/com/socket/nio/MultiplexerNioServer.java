package com.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerNioServer implements Runnable {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;//时间内轮询
    private volatile boolean stop=false;

    /*初始化多路复用器  绑定监听器*/
    public MultiplexerNioServer(int port){
        try{
            serverSocketChannel=ServerSocketChannel.open();//相当于一个酒店
            selector=Selector.open();//相当于一个服务员

            serverSocketChannel.configureBlocking(false);//设置为非阻塞
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);//绑定一个端口和等待队列
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//把selector注册到channel,关注链接事件

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void stop(){
        this.stop=true;
    }




    public void run(){
        while (!stop) {

            try {
                int client = selector.select();//阻塞 epoll 活跃的事件
                if (client == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys= selector.selectedKeys();
                Iterator<SelectionKey> It=selectionKeys.iterator();
                SelectionKey key=null;
                while (It.hasNext()){
                    key=It.next();
                    It.remove();
                    try {
                        handle(key);//单线程效率比较低，可以考虑多线程处理数据

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void handle(SelectionKey key) throws IOException{
        //接受 读 写 断开
        if (key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel ssc= (ServerSocketChannel)key.channel();
                SocketChannel sk=ssc.accept();
                sk.configureBlocking(false);
                sk.register(selector,SelectionKey.OP_READ);//对于当前这个读事件感兴趣
            }

            if(key.isReadable()){
                SocketChannel sc=(SocketChannel) key.channel();

                ByteBuffer readbuffer=ByteBuffer.allocate(1024);
                int rb=sc.read(readbuffer);

                if(rb>0){
                    readbuffer.flip();//buffer.flip();一定得有，如果没有，就是从文件最后开始读取的，当然读出来的都是byte=0时候的字符。
                    // 通过buffer.flip();这个语句，就能把buffer的当前位置更改为buffer缓冲区的第一个位置。

                    //线程池
                    byte[] bytes=new byte[readbuffer.remaining()];
                    readbuffer.get(bytes);
                    String body=new String(bytes,"UTF-8");
                    System.out.println("input is:"+body);
                    res(sc,body);

                }
            }
        }

    }
    private void res(SocketChannel channel,String response)throws IOException{
        if(response!=null&&response.length()>0){
            byte[]bytes=response.getBytes();
            ByteBuffer writeBuffer=ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);

            System.out.println("res end");

        }
    }
}
