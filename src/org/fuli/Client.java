package org.fuli;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket(PublicMethod.HOST, PublicMethod.PORT);
        System.out.println("Client Connected...........");
        //开启新线程用于接收Server发来的消息
        PublicHandler.startReceiveThread(client,"Server");
        PublicHandler.startSendThread(client);
    }
}
