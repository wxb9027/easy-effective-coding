package easy.effective.coding.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间服务器
 * 当client 发送 QUERY TIME ORDER 时，返回最新的系统时间；
 */
public class TimeServerSocket {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("server is start,port:8888:");
            Socket acceptSocket = serverSocket.accept();
            InputStream input = acceptSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(acceptSocket.getInputStream()));
            System.out.println("wait cli msg");
            System.out.println("client msg:" + reader.readLine());
            PrintWriter out = new PrintWriter(acceptSocket.getOutputStream(),true);
            System.out.println("wait to send time to cli");
            out.println(new Date(System.currentTimeMillis()).toString());
            out.flush();
            System.out.println("sent time to cli");

            TimeUnit.SECONDS.sleep(10000);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
