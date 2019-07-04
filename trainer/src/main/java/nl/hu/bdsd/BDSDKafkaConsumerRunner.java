package nl.hu.bdsd;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BDSDKafkaConsumerRunner{
  private static boolean m_test     = false;
  private static String m_testDir   = "test",
                        m_topic     = "sentences";

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

    for(int i = 0; i < files.length; ++i)
      if(files[i].isDirectory()){
        if(m_test) fileMap.put(m_testDir, getFiles(files[i].getName()));
        else
          if(!files[i].getName().equals(m_testDir))
            fileMap.put(files[i].getName(), getFiles(files[i].getName()));
      }
    return fileMap;
  }

  public static void main(String[] args) {
    LanguageTrainer trainer     = new LanguageTrainer(fileMap());
    LanguageAnalyzer analyzer   = new LanguageAnalyzer(m_topic, trainer.getBigramMatrixMap());
    trainer.info();
    analyzer.start();
    System.out.println("\n####################\n#\tLISTENING\t #\n####################\n");
    System.out.println("Listening for sentences on topic: " + m_topic);
    System.out.println("----------------------------------------------------------------");
  }
}
