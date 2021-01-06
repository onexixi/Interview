package IOTest;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;


public class TestBlockingNIO {
    @Test
    public void client() throws IOException {
        //获取通道
        SocketChannel sClannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9099));
        //切换为非阻塞
        sClannel.configureBlocking(false);
        //分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //发送数据给服务端
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
           String string=scanner.next();
            buffer.put((new Date().toString()+"\n"+string).getBytes());
            buffer.flip();
            sClannel.write(buffer);
            buffer.clear();
        }



        sClannel.close();

    }

    //服务端
    @Test
    public void server() throws IOException {
        //获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //非阻塞式
        ssChannel.configureBlocking(false);

        //绑定连接
        ssChannel.bind(new InetSocketAddress(9099));

        //获取选择器
        Selector selector = Selector.open();

        //将通道注册到选择器上
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
//轮询式的获取选择器上已经准备就绪的事件
        while (selector.select() > 0) {
            //获取当前选择器上所有注册的“选择键就绪的监听事件”
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()) {
                //获取就绪的事件
                SelectionKey sk = it.next();
                //判断是什么准备就绪
                if (sk.isAcceptable()) {
                    //获取客户端连接
                    SocketChannel socketChannel = ssChannel.accept();
                    socketChannel.configureBlocking(false);
                    //将通道注册到该选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);


                } else if (sk.isReadable()) {
                    //获取当前选择器上就绪的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();

                    //读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }
            }
            //取消选择器
            it.remove();
        }


    }

}
