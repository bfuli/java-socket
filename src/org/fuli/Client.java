package org.fuli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket(PublicMethod.HOST, PublicMethod.PORT);
        System.out.println("Client Connected...........");
        //开启新线程用于接收Server发来的消息
        PublicHandler.startReceiveThread(client,"Server");
        PublicHandler.startSendThread(client);
    }


//    static class ClientSend implements Runnable{
//        private Socket client;
//        private BufferedReader reader;
//        private String sendText = null;
//        private InputStream is;
//        private OutputStream os;
//        public ClientSend(OutputStream os, InputStream is){
//            this.os = os;
//            this.is = is;
//        }
//        @Override
//        public void run() {
//            while (true){
//                try {
//                    reader = new BufferedReader(new InputStreamReader(System.in));
//                    sendText = reader.readLine();
//                    System.out.println("Client控制台接收："+ sendText);
//                    if (sendText != null){
//                        if (sendText.equals("end")) break;
//                        DataOutputStream out = new DataOutputStream(os);
//                        out.writeUTF(client.getLocalSocketAddress()+ ":" + sendText);
////                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
////                        writer.write(sendText);
////                        writer.flush();
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    static class ClientAccept implements Runnable{
//
//        private Socket client;
//        private InputStream is;
//        private OutputStream os;
//        public ClientAccept(OutputStream os, InputStream is){
//            this.os = os;
//            this.is = is;
//        }
//
//        @Override
//        public void run() {
//            while (true){
//                try {
//                    InputStream inFromServer = is;
//                    DataInputStream in = new DataInputStream(inFromServer);
//                    System.out.println("服务器响应： " + in.readUTF());
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
