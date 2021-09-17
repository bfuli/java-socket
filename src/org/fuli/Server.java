package org.fuli;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PublicMethod.PORT);
        System.out.println("Server started..........");
        Socket server = serverSocket.accept();
        System.out.println("Server connected..........");
        PublicHandler.startSendThread(server);
        PublicHandler.startReceiveThread(server, "Client");
    }
}
