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

  private static final String KAFKA_SERVER_URL                      = "localhost";
  private static final int KAFKA_SERVER_PORT                        = 9092;
  private static final String CLIENT_ID                             = "BDSDKafkaTrainer";
  private static final HashMap<String, HashSet<String>> filenames   = new HashMap<String, HashSet<String>>();
  private HashMap<String, float[][]> bigramMapProbability           = new HashMap<String, float[][]>();
  private HashMap<String, int[][]> bigramMap                        = new HashMap<String, int[][]>();
  private int messageNo                                             = 1;
  private int size                                                  = 27;

  public BDSDKafkaTrainer(String topic, Boolean isAsync, HashMap<String, HashSet<String>> filenames) {
    Properties properties = new Properties();
    properties.put("bootstrap.servers", KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
    properties.put("client.id", CLIENT_ID);
    properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
    properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    this.producer = new KafkaProducer<>(properties);
    this.topic    = topic;
    this.isAsync  = isAsync;
    this.filenames.putAll(filenames);
    initBigramMatrix();
  }
  private int getBigramTotal(String lang){
    int count = 0;
    int[][] matrix = bigramMap.get(lang);
    for(int i = 0; i < matrix.length; ++i){
      for(int j = 0; j < matrix.length; ++j){
        count += matrix[i][j];
      }
    }
    return count;
  }
  /**! a-z over 0 - 26; 27 is '_'(-2); everything else is 27; */
  private int getCode(char c){
    int code = c - 97;
    return (code == -2 ? 26 : (code >= 0 && code <= 25 ? code : 27));
  }
  private String formatter(String s){
      return "_" + s + "_";
  }
  private void initBigramMatrix(){
    for(Map.Entry<String, HashSet<String>> fileMap : filenames.entrySet()){
      int[][] i_matrix    = new int[size+1][size+1];
      float[][] f_matrix  = new float[size+1][size+1];
      bigramMap.put(fileMap.getKey(), i_matrix);
      bigramMapProbability.put(fileMap.getKey(), f_matrix);
    }
  }
  private void updateBigramMatrix(String filename, String lang) throws IOException {
      URL url     = getClass().getResource(filename);
      File file   = new File(url.getPath());
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
          String line = "";
          while ((line = br.readLine()) != null) {
              if (!line.startsWith("#")) {
                  for(String w : Arrays.asList(line.split("\\s|,|\\.|\\?|\\!|\\:|\\-|’|‘|\\(|\\)"))){
                    String word = w.toLowerCase();
                    if(word.matches("[a-z]+")){
                      String f_word = formatter(word);
                      for(int i = 0; i < f_word.length()-1; ++i){
                        ++bigramMap.get(lang)[getCode(f_word.charAt(i))][getCode(f_word.charAt(i+1))];
                      }
                    }
                  }
              }
          }
      }
  }
  private void constructBigramMatrixProbability(){
    for(String lang : bigramMap.keySet()){
      float[][] matrix = bigramMapProbability.get(lang);
      for(int i = 0; i < size; ++i)
        for(int j = 0; j < size; ++j)
          matrix[i][j] = (float)bigramMap.get(lang)[i][j] / (float)getBigramTotal(lang) * 100;
      bigramMapProbability.put(lang, matrix);
    }
  }
  private String stringifyBigramMatrixProbability(String lang){
    String result = lang+",";
    for(int i = 0; i < size; ++i)
      for(int j = 0; j < size; ++j)
        result += (bigramMapProbability.get(lang)[i][j] + (((i+1)*(j+1) < size*size-1) ? "," : ""));
    return result;
  }
  private void info(){
    String header = "\n###################\n#\tINFO\t\t#\n###################\n";
    String body   = "\n####################\n#\tMATRICES\t #\n####################\n";
    char[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '_', '*'};
    int size          = 27;

    System.out.println(header + "\nFILES USED: ");
    for(Map.Entry<String, HashSet<String>> fileMap : filenames.entrySet()){
      String lang = fileMap.getKey();
      System.out.println("\t-> "+ lang + " (" + getBigramTotal(lang) + ")");
      for(String file : fileMap.getValue()){
        System.out.println("\t\t"+ "- " + file);
      }
    }
    System.out.println(body);
    for(String lang : filenames.keySet()){
      System.out.print("\nLang: " + lang + "\n\t");
      for(int i = 0; i < size; ++i) System.out.print(characters[i] + "\t");
      System.out.println("");

      for(int i = 0; i < size; ++i){
        System.out.print(characters[i] + "\t");
        for(int j = 0; j < size; ++j){
          System.out.printf("%.2f\t", bigramMapProbability.get(lang)[i][j]);
        }
        System.out.println("");
      }
    }
  }
  private void send(){
    String tail   = "\n####################\n#\tSENDING\t #\n####################\n";
    System.out.println(tail);
    for(String lang : bigramMap.keySet()){
      String messageStr = stringifyBigramMatrixProbability(lang);
      long startTime    = System.currentTimeMillis();
      System.out.println(messageNo + ") Sending matrix(" + lang + ") to " + topic + " @ " + startTime);
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
      for(Map.Entry<String, HashSet<String>> fileMap : filenames.entrySet()){
        String lang = fileMap.getKey();
        for(String file : fileMap.getValue())
          updateBigramMatrix("/" + fileMap.getKey() + "/" + file, lang);
      }
    }
    catch(IOException e){
      e.printStackTrace();
    }
    constructBigramMatrixProbability();
    info();
    send();
    System.exit(0);
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
