package easy.effective.coding.nio.bufferdemo;

import java.nio.ByteBuffer;

/**
 * 冲区本质上是一个可以写入数据的内存块（类似数组），然后可以再次读取。此内存块包含在NIO Buffer对象中，该对象提供了一组方法，可以更轻松的使用内存块。
 * 相对于直接操作数组，Buffer API提供了更加容易的操作和管理，其进行数据的操作分为写入和读取，主要步骤如下：
 *
 * 将数据写入缓冲区
 * 调用buffer.flip()，转换为读取模式
 * 缓冲区读取数据
 * 调用buffer.clear()或buffer.compact()清楚缓冲区
 * Buffer中有三个重要属性：
 * capacity（容量）：作为一个内存块，Buffer具有一定的固定大小，也称为容量
 * position（位置）：写入模式时代表写数据的位置，读取模式时代表读取数据的位置
 * limit（限制）：写入模式等于Buffer的容量，读取模式时等于写入的数据量
 */

public class BufferDemo {
    public static void main(String[] args) {
        // 构建一个byte字节缓冲区，容量是4
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        // 默认写入模式，查看三个重要的指标
        System.out.println(
                String.format(
                        "初始化：capacity容量：%s, position位置：%s, limit限制：%s",
                        byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));
        // 写入数据
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);
        // 再次查看三个重要的指标
        System.out.println(
                String.format(
                        "写入3字节后后：capacity容量：%s, position位置：%s, limit限制：%s",
                        byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));

        // 转换为读取模式(不调用flip方法，也是可以读取数据的，但是position记录读取的位置不对)
        System.out.println("开始读取");
        byteBuffer.flip();
        byte a = byteBuffer.get();
        System.out.println(a);
        byte b = byteBuffer.get();
        System.out.println(b);
        System.out.println(
                String.format(
                        "读取2字节数据后，capacity容量：%s, position位置：%s, limit限制：%s",
                        byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));

        // 继续写入3字节，此时读模式下，limit=3，position=2.继续写入只能覆盖写入一条数据
        // clear()方法清除整个缓冲区。compact()方法仅清除已阅读的数据。转为写入模式
        byteBuffer.compact();
        // 清除了已经读取的2字节，剩余1字节，还可以写入3字节数据
        // 多写的话会报java.nio.BufferOverflowException异常
        byteBuffer.put((byte) 3);
        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 5);
        System.out.println(
                String.format(
                        "最终的情况，capacity容量：%s, position位置：%s, limit限制：%s",
                        byteBuffer.capacity(), byteBuffer.position(), byteBuffer.limit()));
    }
}