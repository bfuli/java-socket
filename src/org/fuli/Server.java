package org.fuli;

import javax.print.DocFlavor;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static int PORT = 8080;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PublicMethod.PORT);
        System.out.println("Server started..........");
        Socket server = serverSocket.accept();
        System.out.println("Server connected..........");
        PublicHandler.startSendThread(server);
        PublicHandler.startReceiveThread(server, "Client");
    }
}
