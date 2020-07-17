package easy.effective.coding.io.bio;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Author TODO
 * @DATE 20/7/11 12:27
 * @Version 1.0
 **/
public class TimeClientSocket {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8888);
            System.out.println("client is ok.");
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println("query time");

            BufferedReader readerMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("wait server time");
            System.out.println("server time is " + readerMsg.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
