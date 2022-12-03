package com.example.service_hi.runtime_lock.java_object;

import org.bouncycastle.asn1.dvcs.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class NIOclassTest {
    public static void main(String[] args) {

    }
    /**
     * NIO(非阻塞IO)
     * 区别	                Stream(InputStream/OutputStream等)	    Channel
     * 支持异步	            不支持	                                支持
     * 是否可双向传输数据	不能，只能单向	                        可以，既可以从通道读取数据，也可以向通道写入数据
     * 是否结合Buffer使用	不能	                                必须结合Buffer使用
     * 性能	                较低	                                较高
     */
    /**
     * Channel的常见类型：
     * 1. FileChannel 2. DatagramChannel 3. SocketChannel 4. ServerSocketChannel，
     * 分别可以对应文件IO、UDP和TCP（Server和Client）。
     *
     * Buffer，故名思意，缓冲区，实际上是一个容器，是一个连续数组。
     * Channel提供从文件、 网络读取数据的渠道，但是读取或写入的数据都必须经由Buffer
     */
    public static void getServerSocketChannel() throws IOException {
        /**
         *  ServerSocketChannel类
         */
        //通过调用 ServerSocketChannel.open() 方法来打开ServerSocketChannel.如：
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //ServerSocketChannel可以设置成非阻塞模式(false),在非阻塞模式下，accept() 方法会立刻返回，如果还没有新进来的连接,返回的将是null
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        //监听新进来的连接
        while(true){

            SocketChannel socketChannel = serverSocketChannel.accept();//这里阻塞住进程。
            if(socketChannel == null) {
                //通过调用ServerSocketChannel.close() 方法来关闭ServerSocketChannel.
                serverSocketChannel.close();
            }else {
                //...
            }
        }
    }
    public static void getSocketChannel() throws IOException {
        /**
         * SocketChannel类，它是使用最多的socket通道类：
         */
        //SocketChannel的打开
        SocketChannel socketChannel = SocketChannel.open();
        //可以设置 SocketChannel 为非阻塞模式（non-blocking mode）.设置之后，就可以在异步模式下调用connect(), read() 和write()了。
        socketChannel.configureBlocking(false);
        //SocketChannel的连接，和”jenkov.com”服务器的UDP端口80建立连接，发送数据
        socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));
        //SocketChannel在非阻塞模式下，此时调用connect()，该方法可能在连接建立之前就返回了。为了确定连接是否建立，可以调用finishConnect()的方法
        while(! socketChannel.finishConnect() ){
            //wait, or do something else...
            /**
             * 从SocketChannel中读取数据。read()方法返回的int值表示读了多少字节进Buffer里。
             * 如果返回的是-1，表示已经读到了流的末尾（连接关闭了）。
             */
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = socketChannel.read(buf);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();
            while(buf.hasRemaining()) {
                //注意SocketChannel.write()方法的调用是在一个while循环中的。Write()方法无法保证能写多少字节到SocketChannel。
                // 所以，我们重复调用write()直到Buffer没有要写的字节为止
                socketChannel.write(buf);
            }
        }


    }

}
