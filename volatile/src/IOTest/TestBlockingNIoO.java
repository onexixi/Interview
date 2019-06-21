package IOTest;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlockingNIoO {
//    public static void main(String[] args) {
//        System.out.println("xxx");
//    }

    //客户端
    @Test
    public void client() throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        FileChannel fileChannel = FileChannel.open(Paths.get("01.jpg"), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (fileChannel.read(buffer) != 1) {
            buffer.flip();
            sChannel.write(buffer);
            buffer.clear();
        }
        //接收服务的反馈
        int len = 0;
        while ((len = sChannel.read(buffer)) != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, len));
            buffer.clear();
        }

        fileChannel.close();
        sChannel.close();
        sChannel.shutdownOutput();

    }


    //服务端
    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        FileChannel fileChannel = FileChannel.open(Paths.get("03.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        serverSocketChannel.bind(new InetSocketAddress(9898));
        SocketChannel schannel = serverSocketChannel.accept();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (schannel.read(buffer) != -1) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }
       //发送反馈
        buffer.put("服务端接收成功".getBytes());
        buffer.flip();
        schannel.write(buffer);


        schannel.close();
        serverSocketChannel.close();
        fileChannel.close();

    }
}
