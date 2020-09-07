package com.socket.bio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServerSingle {
    public static void main(String[] args) {

        ServerSocket server=null;//服务端
        Socket socket=null;//客户端

        InputStream inputStream=null;
        OutputStream outputStream=null;

        try {
            server=new ServerSocket(8088);
            while (true){
             /*   socket=server.accept();
                inputStream=socket.getInputStream();
                int lenth=0;
                byte[] req=new byte[1024];
                while ((lenth=inputStream.read(req))>0){
                    System.out.println(new String(req,0,lenth));

                    //servlet
                    outputStream=socket.getOutputStream();
                    outputStream.write("res".getBytes());
                }*/

             //有什么方案 能接收到多个客户端请求
                new Thread(new SocketHandler(socket)).start();//10000 客户端同时来 cpu 要被拖垮 要用线程池
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(server!=null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
