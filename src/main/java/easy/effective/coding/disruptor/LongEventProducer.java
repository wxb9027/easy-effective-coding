package easy.effective.coding.disruptor;


import com.lmax.disruptor.RingBuffer;
import org.apache.log4j.Logger;

import java.nio.ByteBuffer;

public class LongEventProducer {
    private static final Logger LOG = Logger.getLogger(LongEventProducer.class);
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence = ringBuffer.next();
        /**
         * 布事件的时候要使用try/finnally保证事件一定会被发布）。
         * 如果我们使用RingBuffer.next()获取一个事件槽，那么一定要发布对应的事件。
         * 如果不能发布事件，那么就会引起Disruptor状态的混乱。
         * 尤其是在多个事件生产者的情况下会导致事件消费者失速，从而不得不重启应用才能会恢复
         */
        try {
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(bb.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }

}
