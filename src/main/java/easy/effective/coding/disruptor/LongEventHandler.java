package easy.effective.coding.disruptor;

import com.lmax.disruptor.EventHandler;
import org.apache.log4j.Logger;

public class LongEventHandler implements EventHandler<LongEvent> {
    private static final Logger LOG = Logger.getLogger(LongEventHandler.class);
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        LOG.info("handler event: "+ longEvent.getValue());
    }
}
