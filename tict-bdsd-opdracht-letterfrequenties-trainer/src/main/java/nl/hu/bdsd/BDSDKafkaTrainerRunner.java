package nl.hu.bdsd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BDSDKafkaTrainerRunner{
  public static final HashSet<String> filenamesNL                     = new HashSet<>();
  public static final HashSet<String> filenamesEN                     = new HashSet<>();
  public static final HashMap<String, HashSet<String>> filenames      = new HashMap<String, HashSet<String>>();

  private static void addDutchFiles() {
      filenamesNL.add("/sentences.nl.txt");
      //filenamesNL.add("/test.nl.txt");
  }

  private static void addEnglishFiles(){
      filenamesEN.add("/sentences.en.txt");
  }
  public static void addFiles(){
    filenames.put("nl", filenamesNL);
    filenames.put("en", filenamesEN);
  }

  public static void main(String[] args) {
      addDutchFiles();
      addEnglishFiles();
      addFiles();
      BDSDKafkaTrainer producerThread = new BDSDKafkaTrainer("training", false, filenames);

      // start the producer
      producerThread.start();
  }
}
