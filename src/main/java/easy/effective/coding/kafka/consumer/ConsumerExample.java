package easy.effective.coding.kafka.consumer;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import easy.effective.coding.kafka.producer.ProducerExample;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerExample implements MQConsumer{
    private KafkaConsumer<String,GenericRecord> consumer;
    @Override
    public void init(Properties mqPro) {
        consumer = new KafkaConsumer<String, GenericRecord>(mqPro);
        //Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary();
    }

    @Override
    public void poll() {
        while (true){
            consumer.subscribe(Collections.singletonList("why-01"));
            ConsumerRecords<String,GenericRecord> records = consumer.poll(Duration.ofMillis(5000));
            for (ConsumerRecord<String, GenericRecord> record : records) {
                GenericRecord user = record.value();
                System.out.println("id: " + user.get("id"));
            }
        }
    }

    @Override
    public void stop() {

    }

    public static void main(String[] args) {
        ProducerExample pe = new ProducerExample();
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.245.131:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group2");
        //props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        // 使用Confluent实现的KafkaAvroSerializer
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        // 添加schema服务的地址，用于获取schema
        props.put("schema.registry.url", "http://192.168.245.131:8081");

        ConsumerExample consumerExample = new ConsumerExample();
        consumerExample.init(props);
        consumerExample.poll();

    }
}
