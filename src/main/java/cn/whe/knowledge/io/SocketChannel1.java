package cn.whe.knowledge.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannel1 {
        public static void main(String[] args) throws IOException {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.bind(new InetSocketAddress("localhost", 5500));
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();
            while(buf.hasRemaining()) {
                socketChannel.write(buf);
            }
            socketChannel.close();
        }
}
