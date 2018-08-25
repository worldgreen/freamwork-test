package cn.whe.knowledge.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class NIOServer {
    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(5500));
            ssc.configureBlocking(false); // 设定为非阻塞

            List<SocketChannel> scList = new ArrayList<>();
            ByteBuffer buffer = ByteBuffer.allocate(48);

            while (true) {
                SocketChannel sc = ssc.accept(); // 此处会阻塞

                if (sc != null) {
                    scList.add(sc);
                    System.out.println("new socket");

                    sc.configureBlocking(false); // 设定为1非阻塞
                }

                for (SocketChannel scTemp : scList) {
                    scTemp.read(buffer); // 此处会阻塞
                }

                buffer.flip();

                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }

                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
