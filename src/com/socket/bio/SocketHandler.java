package com.socket.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketHandler implements Runnable {
    private Socket socket;

    public SocketHandler(Socket socket){
        this.socket=socket;
    }

    public void run(){
        InputStream in=null;
        OutputStream out=null;

        try {
            in=socket.getInputStream();
                int lenth=0;
                byte[] req=new byte[1024];
                while ((lenth=in.read(req))>0){
                    System.out.println("input is:"+new String(req,0,lenth));

                    //servlet
                    out=socket.getOutputStream();
                    out.write("success".getBytes());
                    System.out.println("end");
                }

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

    }

}
