package cn.whe.knowledge.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class IOServer {

    private static PrintWriter pw;
    private static ServerSocket serverSocket;
    private static Socket s;
    private static Scanner scanner = new Scanner(System.in);
    private static BufferedReader br;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(5500);
            System.out.println("server ok");
            s = serverSocket.accept();    // 阻塞
            System.out.println("connection ok");
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

            while (true) {
                String str = br.readLine();
                System.out.println("server read " + str);
                System.out.println("server require input");
                String write = scanner.nextLine();
                pw.println(write);
                pw.flush();
                if (write.equals("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
