package org.fuli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PublicHandler{
    //结束标记字符
    private static String END = "end";

    /**
     * 开启一个专门负责接收消息的线程
     * @param socket：用于接收消息的Socket
     * @param socketName：表示从哪一端发送的标记。如果socket=Server，socketName=ClientName，反之一样
     */
    public static void startReceiveThread(Socket socket, String socketName){
        new Thread(new Runnable() {
            private PublicMethod method = new PublicMethod();
            @Override
            public void run() {
                while (true) {
                    String msg = method.receiveMsg(socket);
                    if (msg != null) {
                        if (END.equals(msg)) {
                            try {
                                socket.close();
                                System.out.println("finished");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(socketName + " Say:" + msg);

                    }
                }
            }
        }).start();
    }

    /**
     * 开启一个专门用于发送消息的线程，接收控制台输入
     * @param socket：要发送消息的Socket
     */
    public static void startSendThread(Socket socket){
        new Thread(new Runnable() {
            private PublicMethod method = new PublicMethod();
            String msg;
            @Override
            public void run() {
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    while (true) {
                        if (((msg = consoleReader.readLine()) != null)) {
                            if (END.equals(msg)) {
                                socket.close();
                                System.exit(0);
                            }
                            method.sendMsg(socket, msg);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
