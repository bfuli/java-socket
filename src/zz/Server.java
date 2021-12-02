package zz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8000;
    private static int i = 0;// 标记客户端序号
    private static final int maxNum = 10; // 允许最大连接线程数

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            if ( i >= maxNum) {
                throw new RuntimeException("线程数目过多");
            }
            Socket server = serverSocket.accept();
            System.out.println("客户端"+ i + "连接成功");

            new Thread(new Runnable() {
                /*此处在高并发场景下有问题，修改为：
                新建Thread类，将num作为构造函数传递给线程实例
                */
                final int num = i;
                @Override
                public void run() {
                    while (true) {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
                            String msg = reader.readLine();
                            if (msg != null) {
                                System.out.println("客户端"+ num + "：" + msg);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            i++;
        }
    }
}
