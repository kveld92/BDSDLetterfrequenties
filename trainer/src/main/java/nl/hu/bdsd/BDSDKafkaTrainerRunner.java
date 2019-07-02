package nl.hu.bdsd;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BDSDKafkaTrainerRunner{
  private static boolean test = false;

  private static HashSet<String> getFiles(String lang) {
    HashSet<String> filenames = new HashSet<>();
    String path               = System.getProperty("user.dir")+"/target/classes/"+lang;
    File folder               = new File(path);
    File[] files              = folder.listFiles();

    for(int i = 0; i < files.length; ++i)
      if(files[i].isFile())
        filenames.add(files[i].getName());

    return filenames;
  }

  public static HashMap<String, HashSet<String>> fileMap(){
    HashMap<String, HashSet<String>> fileMap    = new HashMap<String, HashSet<String>>();
    String path                                 = System.getProperty("user.dir")+"/target/classes/";
    File folder                                 = new File(path);
    File[] files                                = folder.listFiles();
    String testDir                              = "test";

    for(int i = 0; i < files.length; ++i)
      if(files[i].isDirectory()){
        if(test) fileMap.put(testDir, getFiles(files[i].getName()));
        else
          if(!files[i].getName().equals("test"))
            fileMap.put(files[i].getName(), getFiles(files[i].getName()));
      }
    return fileMap;
  }

  public static void main(String[] args) {
    BDSDKafkaTrainer producerThread = new BDSDKafkaTrainer("training", false, fileMap());
    // start the producer
    producerThread.start();
  }
}
