package nl.hu.bdsd;

import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;

public class LanguageAnalyzer extends ShutdownableThread{
  private final KafkaConsumer<Integer, String> consumer;
  private final String topic;
  public static final String KAFKA_SERVER_URL             = "localhost";
  public static final int KAFKA_SERVER_PORT               = 9092;
  public static final String CLIENT_ID                    = "BDSDKafkaTrainerAnalyzer";

  private HashMap<String, BigramMatrix> bigramMatrixMap   = new HashMap<String, BigramMatrix>();

  public LanguageAnalyzer(String topic, HashMap<String, BigramMatrix> bigramMatrixMap) {
    super(CLIENT_ID, false);
    Properties props = new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, CLIENT_ID);
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
    consumer              = new KafkaConsumer<>(props);
    this.topic            = topic;
    this.bigramMatrixMap  = bigramMatrixMap;
  }
  @Override
  public void doWork() {
      consumer.subscribe(Collections.singletonList(this.topic));
      for (ConsumerRecord<Integer, String> record : consumer.poll(1000)) {
        BigramMatrix bigramMatrixRecord = new BigramMatrix(record.value());
        double minDeviation = Double.POSITIVE_INFINITY;
        String probableLang = "";
        for(String lang : bigramMatrixMap.keySet()){
          double deviation = bigramMatrixMap.get(lang).compare(bigramMatrixRecord);
          if(deviation < minDeviation){
            minDeviation = deviation;
            probableLang = lang;
          }
        }
        System.out.println("(" + probableLang.toUpperCase() + ")\t" + record.value());
      }
  }

  @Override
  public String name() {
      return null;
  }

  @Override
  public boolean isInterruptible() {
      return false;
  }
}
