package nl.hu.bdsd;

import java.io.*;
import java.util.*;

public class BigramMatrix {
  /*********
    PRIVATE:
  *********/
    private String m_text             = "";
    private int m_size                = 27,
                frequencyTotal      = 0;
    private float[][] m_bigramMatrix  = new float[m_size+1][m_size+1];

    /**! a-z over 0 - 26; 27 is '_'(-2); everything else is 27; */
    private int getCode(char c){ return ((c - 97) == -2 ? 26 : ((c - 97) >= 0 && (c - 97) <= 25 ? (c - 97) : 27)); }

    private String formatter(String s){ return "_" + s + "_"; }

    private void frequencyTotal(){
      for(int i = 0; i < m_bigramMatrix.length; ++i)
        for(int j = 0; j < m_bigramMatrix.length; ++j)
          frequencyTotal += m_bigramMatrix[i][j];
    }
    // Parse m_text to frequency table
    private void parseText(){
      for(String w : Arrays.asList(m_text.split("\\s|,|\\.|\\?|\\!|\\:|\\-|’|‘|\\(|\\)"))){
        String word = w.toLowerCase();
        if(word.matches("[a-z]+")){
          String f_word = formatter(word);
          for(int i = 0; i < f_word.length()-1; ++i){
            ++m_bigramMatrix[getCode(f_word.charAt(i))][getCode(f_word.charAt(i+1))];
          }
        }
      }
    }
    private void probabilitify(){
      for(int i = 0; i < m_bigramMatrix.length; ++i)
        for(int j = 0; j < m_bigramMatrix.length; ++j)
          m_bigramMatrix[i][j] = m_bigramMatrix[i][j] / frequencyTotal * 100;
    }
  /********
    PUBLIC:
  ********/
    public BigramMatrix(String m_text){ this.m_text = m_text; parseText(); frequencyTotal(); probabilitify(); }

    public int getFrequencyTotal(){ return frequencyTotal; }

    // the lower the deviation the higher the chance
    public double compare(BigramMatrix bigramMatrix){
      double deviation = 0;
      for(int i = 0; i < m_size; ++i)
        for(int j = 0; j < m_size; ++j)
          deviation += Math.abs(bigramMatrix.getProbabilityMatrix()[i][j] - m_bigramMatrix[i][j]);
      return deviation;
    }

    public void info(){
      char[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '_'};
      System.out.print("\t");
      for(int i = 0; i < m_bigramMatrix.length - 1; ++i) System.out.print(characters[i] + "\t");
      System.out.println("");
      for(int i = 0; i < m_bigramMatrix.length - 1; ++i){
        System.out.print(characters[i] + "\t");
        for(int j = 0; j < m_bigramMatrix.length - 1; ++j){
          System.out.printf("%.2f\t", m_bigramMatrix[i][j]);
        }
        System.out.println("");
      }
    }
    public float[][] getProbabilityMatrix(){ return m_bigramMatrix; }

}
