package cn.whe.knowledge.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class IOClient {

    private static PrintWriter pw = null;
    private static BufferedReader br = null;
    private static Socket s = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            s = new Socket(InetAddress.getLocalHost(), 5500);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(s.getOutputStream());
            while (true) {
                System.out.println("client input");
                String str = scanner.nextLine();
                pw.println(str);
                pw.flush();
                String read = br.readLine();
                System.out.println("client output" + read);
                if (str.equals("exit")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
