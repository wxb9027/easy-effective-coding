package easy.effective.coding.nio;

import org.junit.jupiter.api.Test;

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

/**
 * 一、使用NIO完成网络通信的三个核心：
 * 1.通道（Channel）：负责连接
 * java.nio.channels.Channel接口：
 * |--SelectableChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * <p>
 * |--Pipe.SinkChannel
 * |--Pipe.SourceChannel
 * <p>
 * 2.缓冲区（Buffer）：负责数据的存取
 * <p>
 * 3.选择器（Selector）：是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况。
 */
public class TestNonBlockingNIO {

    public static void main(String[] args) throws IOException {
        //1.获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9998));

        //2.切换为非阻塞模式
        sChannel.configureBlocking(false);

        //3.分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String str = scan.next();
            //4.发送数据给服务端
            buf.put((new Date().toString() + "\n" + str).getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }

        //5.关闭通道
        sChannel.close();
    }

    //客户端（idea启动junit单元测试不能通过console输入数据，先用main方法验证）
    @Test
    public void client() throws IOException {

    }

    //服务端
    @Test
    public void server() throws IOException {
        //1.获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2.切换为非阻塞模式
        ssChannel.configureBlocking(false);

        //3.绑定连接
        ssChannel.bind(new InetSocketAddress(9998));

        //4.获取选择器
        Selector selector = Selector.open();
        //5.将通道注册到选择器上，且指定监测事件（此处指定监听接收事件）
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6.轮询式的获取选择器上已经准备就绪的事件
        while (selector.select() > 0) {
            //7.获取当前选择器中所有已注册的 选择器（已就绪的监听事件）
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                //8.迭代获取
                SelectionKey sk = it.next();
                //9.判断具体式什么事件准备就绪
                if (sk.isAcceptable()) {
                    //10.若接收就绪
                    SocketChannel sChannel = ssChannel.accept();
                    //11.切换非阻塞
                    sChannel.configureBlocking(false);
                    //12.将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    //13.获取当前选择器上”读就绪“状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    //14.读数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                //15.取消选择键SelectionKey
                it.remove();
            }
        }

    }
}

