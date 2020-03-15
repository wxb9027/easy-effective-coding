package easy.effective.coding.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Disruptor 3.0提供了lambda式的API。
 * 这样可以把一些复杂的操作放在Ring Buffer，
 * 所以在Disruptor3.0以后的版本最好使用Event Publisher或者Event Translator来发布事件。
 *
 * 上面写法的另一个好处是，Translator可以分离出来并且更加容易单元测试。
 * Disruptor提供了不同的接口(EventTranslator, EventTranslatorOneArg, EventTranslatorTwoArg,
 * 等等)去产生一个Translator对象。很明显，Translator中方法的参数是通过RingBuffer来传递的。
 */
public class LongEventProducerWithTranslator {
    //一个translator可以看做一个事件初始化器，publicEvent方法会调用它
    //填充Event
    private static final EventTranslatorOneArg<LongEvent,ByteBuffer> TRANSLATOR_ONE_ARG = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
        @Override
        public void translateTo(LongEvent longEvent, long l, ByteBuffer byteBuffer) {
            longEvent.setValue(byteBuffer.getLong(0));
        }
    };

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer){
        ringBuffer.publishEvent(TRANSLATOR_ONE_ARG,byteBuffer);
    }


}

