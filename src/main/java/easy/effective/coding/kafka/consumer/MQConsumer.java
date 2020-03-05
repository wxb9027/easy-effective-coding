package easy.effective.coding.kafka.consumer;

import java.util.Properties;

public interface MQConsumer {
    void init(Properties mqPro);
    void poll();
    void stop();

}
