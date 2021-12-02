package zz;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;
    private static final String END = "end";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORT);
        System.out.println("Connected...........");

        new Thread(new Runnable() {
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
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                            writer.write(msg + "\n");
                            writer.flush();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
