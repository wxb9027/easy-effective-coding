package easy.effective.coding.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 最后一步就是把所有的代码组合起来完成一个完整的事件处理系统。
 * Disruptor在这方面做了简化，
 * 使用了DSL风格的代码（其实就是按照直观的写法，不太能算得上真正的DSL）。
 * 虽然DSL的写法比较简单，但是并没有提供所有的选项。
 * 如果依靠DSL已经可以处理大部分情况了。
 */
public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        Executor consumerExecutor = Executors.newCachedThreadPool();
        // The factory for the event
        LongEventFactory longEventFactory = new LongEventFactory();
        // Specify the size of the ring buffer, must be power of 2
        int bufferSize = 1024;
        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(longEventFactory, bufferSize, consumerExecutor);
        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            byteBuffer.putLong(0, l);
            producer.onData(byteBuffer);
            //Thread.sleep(1000);
        }

    }
}
