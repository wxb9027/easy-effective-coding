package easy.effective.coding.nio;

import java.lang.String;
import org.junit.jupiter.api.Test;
import scala.util.control.Exception;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区（Buffer）：在java NIO中负责存取数据。缓冲区就是数组。用于存储不同数据类型的数据。
 * 根据数据类型不同，提供了相应类型的缓冲区。
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，都是通过allocate()方法获取缓冲区。
 *
 * 二、缓冲区存取数据的二个核心方法:
 * put()：存入数据到缓冲区中
 * get()：从缓冲区中取出数据
 *
 * 缓冲区中的四个核心属性
 * capacity：容量，表示缓冲区最大存储数据的容量，一旦申明不能改变。
 * limit：界限，表示缓冲区中可以操作数据的大小（limit后面的数据不可读写）
 * position：位置，表示缓冲区中正在操作数据的位置。
 *
 * mark: 标记，表示记录当前postion的位置，可以通过reset恢复到mark的位置。
 *
 * 0 <= mark <= position <= limit <= capacity
 *
 *  非直接缓冲区：通过allocate()方法分配，将缓冲区建立在jvm的内存中。
 *  直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率。
 */
public class TestBuffer {

    @Test
        public void test3(){
        ByteBuffer buff = ByteBuffer.allocateDirect(1024);
        System.out.println(buff.isDirect());
    }

    @Test
    public void test2(){
        String str = "abcde";

        ByteBuffer buff = ByteBuffer.allocate(1024);

        buff.put(str.getBytes());

        buff.flip();

        byte[] dst = new byte[buff.limit()];
        buff.get(dst,0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buff.position());

        //标记
        buff.mark();

        buff.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buff.position());

        //reset恢复到mark的位置
        buff.reset();
        System.out.println(buff.position());

        //判断缓冲区中是否还有可操作的数据
        if(buff.hasRemaining()){
            //如果有，获取剩余可操作数据的个数
            System.out.println(buff.remaining());
        }

    }

    @Test
    public void test1(){
        String str = "abcde";

        //1.分配一个缓冲区
        ByteBuffer buff = ByteBuffer.allocate(1024);

        System.out.println("-----------allocate-------------");
        System.out.println(buff.capacity());
        System.out.println(buff.limit());
        System.out.println(buff.position());

        //2.利用put方法存入数据到缓冲区
        buff.put(str.getBytes());

        System.out.println("-----------put-------------");
        System.out.println(buff.capacity());
        System.out.println(buff.limit());
        System.out.println(buff.position());

        //3.切换成读数据模式
        buff.flip();
        System.out.println("-----------flip-------------");
        System.out.println(buff.capacity());
        System.out.println(buff.limit());
        System.out.println(buff.position());

        //4.读取数据
        byte[] dst = new byte[buff.limit()];
        buff.get(dst);
        System.out.println(new String(dst, 0, dst.length));

        System.out.println("-----------get-------------");
        System.out.println(buff.capacity());
        System.out.println(buff.limit());
        System.out.println(buff.position());

        //5.rewind()可重复读数据
        buff.rewind();
        System.out.println("-----------rewind-------------");
        System.out.println(buff.capacity());
        System.out.println(buff.limit());
        System.out.println(buff.position());

        //6.清空缓冲区。指针位置回归初始位，但数据没有被清空，只是处于“被遗忘”状态。
        buff.clear();
        System.out.println("-----------clear-------------");
        System.out.println(buff.capacity());
        System.out.println(buff.limit());
        System.out.println(buff.position());

        System.out.println((char)buff.get(0));

    }

}
