package nl.hu.bdsd;

import java.io.*;
import java.net.URL;
import java.util.*;

public class LanguageTrainer {
  /*********
    PRIVATE:
  *********/
    private static final HashMap<String, HashSet<String>> m_filenames   = new HashMap<String, HashSet<String>>();
    private HashMap<String, BigramMatrix> m_bigramMatrixMap             = new HashMap<String, BigramMatrix>();

    private BigramMatrix fileToBigramMatrix(String filename) throws IOException {
        URL url     = getClass().getResource(filename);
        File file   = new File(url.getPath());
        String text = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("#")) {
                  text += line;
                }
            }
        }
        return new BigramMatrix(text);
    }

  /********
    PUBLIC:
  ********/
    public LanguageTrainer( HashMap<String, HashSet<String>> m_filenames) {
      this.m_filenames.putAll(m_filenames);
      try{
        for(Map.Entry<String, HashSet<String>> fileMap : m_filenames.entrySet()){
          String lang = fileMap.getKey();
          for(String file : fileMap.getValue())
            m_bigramMatrixMap.put(lang, fileToBigramMatrix("/" + fileMap.getKey() + "/" + file));
        }
      }
      catch(IOException e){ e.printStackTrace(); }
    }

    public HashMap<String, BigramMatrix> getBigramMatrixMap(){ return m_bigramMatrixMap; }

    public void info(){
      String header = "\n###################\n#\tINFO\t\t#\n###################\n";
      String body   = "\n####################\n#\tMATRICES\t #\n####################\n";
      char[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '_', '*'};

      System.out.println(header + "\nFILES USED: ");
      for(Map.Entry<String, HashSet<String>> fileMap : m_filenames.entrySet()){
        String lang = fileMap.getKey();
        System.out.println("\t-> "+ lang + " (" + m_bigramMatrixMap.get(lang).getFrequencyTotal() + ")");
        for(String file : fileMap.getValue()){
          System.out.println("\t\t"+ "- " + file);
        }
      }
      System.out.println(body);
      for(String lang : m_filenames.keySet()){
        System.out.print("\nLang: " + lang + "\n");
        m_bigramMatrixMap.get(lang).info();
      }
    }
}
