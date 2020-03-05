package easy.effective.coding.kafka.producer;

import java.util.Properties;

public interface MQProducer {
    void init(Properties mqPro);
    void send();
    void stop();
    void reconnect();
}
