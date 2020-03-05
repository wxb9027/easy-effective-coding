package easy.effective.coding.kafka.producer;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerExample implements MQProducer {
    public static final String USER_SCHEMA = "{\"type\": \"record\", \"name\": \"User\", " +
            "\"fields\": [{\"name\": \"id\", \"type\": \"int\"}, " +
            "{\"name\": \"name\",  \"type\": \"string\"}, {\"name\": \"age\", \"type\": \"int\"}]}";

    //private static Producer<String, byte[]> producer;
    private static Producer<Object, Object> producer;
    Schema.Parser parser = new Schema.Parser();
    Schema schema = parser.parse(USER_SCHEMA);

    private static final Lock lock = new ReentrantLock();
    @Override
    public void init(Properties mqPro) {
        lock.lock();
        try{
            try {
                if (null == producer) {
                    producer = new KafkaProducer<Object, Object>(mqPro);
                    System.out.println("kafka producer 初始化成功.");
                } else {
                    System.out.println("kafka producer 已初始化完成，无需重复.");
                }
            } finally {
                lock.unlock();
            }
        }catch (Exception e){
            new RuntimeException("kafka producer 初始化失败",e);
        }

    }
    @Override
    public void send() {
        String key = "key1";
        GenericRecord user = new GenericData.Record(schema);
        Random rand = new Random();
        user.put("id",rand.nextInt(1000));
        user.put("age",rand.nextInt(27));
        user.put("name","xiaoming");

        //Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);
        //byte[] userByte = recordInjection.apply(user);
        ProducerRecord<Object, Object> record = new ProducerRecord<>("why-01", key,user);
        try {
            this.producer.send(record);
            System.out.println("数据发送成功.");
        }catch (SerializationException e){
            //TODO 需要处理
            System.out.println("kafka producer 序列化失败！");
        }

    }

    @Override
    public void stop() {
        producer.flush();
        producer.close();
    }

    @Override
    public void reconnect() {

    }

    public static void main(String[] args) {
        ProducerExample pe = new ProducerExample();
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.245.131:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        // 使用Confluent实现的KafkaAvroSerializer
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        // 添加schema服务的地址，用于获取schema
        props.put("schema.registry.url", "http://192.168.245.131:8081");
        pe.init(props);
        for (int i=0;i<10;i++){
            pe.send();
        }
        pe.stop();


    }
}
