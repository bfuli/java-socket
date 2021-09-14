package org.fuli;

import java.io.*;
import java.net.Socket;

public class PublicMethod {
    public static String HOST = "127.0.0.1";
    public static int PORT = 8080;
    public PublicMethod(){
    }

    /**
     * 发送消息
     * @param send：要发送的消息
     */
    public void sendMsg(Socket socket, String send){
        if (send != null){
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                //注意：此处的换行符"\n"不能缺少，因为BufferedReader.readLine()以换行符为结束标志，
                //缺少"\n"导致方法阻塞
                writer.write(send + "\n");
                writer.flush();
                //使用下面方法对换行符"\n"没有强制要求
//                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//                out.writeUTF(send);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 接收消息
     * @return
     */
    public String receiveMsg(Socket socket){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return reader.readLine();
//            DataInputStream in = new DataInputStream(socket.getInputStream());
//            return in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
