package nl.hu.bdsd;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class BDSDKafkaTrainer extends Thread{
  private final KafkaProducer<Integer, String> producer;
  private final String topic;
  private final Boolean isAsync;

  private static final String KAFKA_SERVER_URL = "localhost";
  private static final int KAFKA_SERVER_PORT = 9092;
  private static final String CLIENT_ID = "BDSDKafkaTrainer";
  private static final HashMap<String, HashSet<String>> filenames = new HashMap<String, HashSet<String>>();

  private HashMap<String, int[][]> bigramMap = new HashMap<String, int[][]>();
  private int messageNo         = 1;
  private int size              = 27;

  public BDSDKafkaTrainer(String topic, Boolean isAsync, HashMap<String, HashSet<String>> filenames) {
      Properties properties = new Properties();
      properties.put("bootstrap.servers", KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
      properties.put("client.id", CLIENT_ID);
      properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
      properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
      producer = new KafkaProducer<>(properties);
      this.topic = topic;
      this.isAsync = isAsync;
      this.filenames.putAll(filenames);
  }
  private int getCode(char c){
    int code = c - 97; // so a starts at 0
    return (code == -2 ? 26 : (code >= 0 && code <= 25 ? code : 27)); // a-z over 0 - 26; 27 is '_'(-2); everything else is 27 whenever that may happen;
  }
  private String formatter(String s){
      return s.toLowerCase().replaceAll("\\p{Punct}|’|‘| ", "_");
  }
  private boolean checkSize(String s){
    return s.length() == 2 && s.matches("^[^0-9]+$");
  }
  private void generateMatrix(String filename, String lang) throws IOException {
      int[][] bigramMatrix  = new int[size+1][size+1];
      URL url = getClass().getResource(filename);
      File file = new File(url.getPath());
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
          String line;
          while ((line = br.readLine()) != null) {
              if (!line.startsWith("#")) {
                  List<String> sequence = Arrays.asList(line.split("(?<=\\G..)"));
                  for(String bigram : sequence){
                    String s = formatter(bigram);
                    if(checkSize(s)) {
                      int x = getCode(s.charAt(0));
                      int y = getCode(s.charAt(1));
                      bigramMatrix[x][y] += 1;
                    }
                  }
              }
          }
      }
      bigramMap.put(lang, bigramMatrix);
  }
  private String stringifyBigramMatrix(String lang){
    String result = lang+",";
    int[][] matrix = bigramMap.get(lang);
    for(int i = 0; i < size; ++i)
      for(int j = 0; j < size; ++j)
        result += (matrix[i][j] + (((i+1)*(j+1) < size*size-1) ? "," : ""));
    return result;
  }
  private void send(){
    for(String lang : bigramMap.keySet()){
      String messageStr = stringifyBigramMatrix(lang);
      long startTime    = System.currentTimeMillis();
      if(isAsync) producer.send(new ProducerRecord<>(topic, messageNo, messageStr), new MyCallback(startTime, messageNo, messageStr));
      else{
        try { producer.send(new ProducerRecord<>(topic, messageNo, messageStr)).get(); }
        catch(InterruptedException | ExecutionException e) { e.printStackTrace(); }
      }
      ++messageNo;
    }
  }
  public void run() {
    try{
      for(String lang : filenames.keySet())
        for(String filename : filenames.get(lang))
          generateMatrix(filename, lang);
    }
    catch(IOException e){
      e.printStackTrace();
    }
    printMatrices();
    send();
    System.exit(0);
  }
  private void printMatrices(){
    char[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '_', '*'};
    int size          = 27;
    for(String lang : filenames.keySet()){
      System.out.print("\nLang: " + lang + "\n\t");
      for(int i = 0; i < size; ++i) System.out.print(characters[i] + "\t");
      System.out.println("");

      int[][] matrix = bigramMap.get(lang);
      for(int i = 0; i < size; ++i){
        System.out.print(characters[i] + "\t");
        for(int j = 0; j < size; ++j){
          System.out.print(matrix[i][j] + "\t");
        }
        System.out.println("");
      }
    }
  }
}
class MyCallback implements Callback {
    private final long startTime;
    private final int key;
    private final String message;
    public MyCallback(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    /**
     * onCompletion method will be called when the record sent to the Kafka Server has been acknowledged.
     *
     * @param metadata  The metadata contains the partition and offset of the record. Null if an error occurred.
     * @param exception The exception thrown during processing of this record. Null if no error occurred.
     */

    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println(
                    "message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                            "), " +
                            "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }
    }
}
