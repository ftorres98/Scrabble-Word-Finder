import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {

   /**
    Representation invariant:

    -- anagramDict is NEVER null
      -- they key is always in alphabetical order
      -- the value contains all words that have the same alphabetical order

    */

   private Map<String, Set<String>> anagramDict;

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
      anagramDict = new HashMap<String, Set<String>>();

      File inFile = new File(fileName);

      Scanner in = new Scanner(inFile);

      while(in.hasNext()){
         String word = in.next().toLowerCase();
         String sortedWord = sortForm(word);

         if(!anagramDict.containsKey(sortedWord)){
            Set<String> wordSet = new HashSet<String>();
            wordSet.add(word);
            anagramDict.put(sortedWord, wordSet);
         }
         else {
            if(!anagramDict.get(sortedWord).add(word)){
               throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + word);
            }
         }

      }
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param string string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      if(anagramDict.get(sortForm(string.toLowerCase())) == null){
         return null;
      }

      return new ArrayList<String>(anagramDict.get(sortForm(string.toLowerCase())));
   }

   /**
    * This method takes in a string and sorts the characters in alphabetical order
    * and returns the resulting string.
    * @param word the string to be sorted
    * @return a string in which the characters are in alphabetical order
    */
   private String sortForm(String word){
      char [] wordArray = word.toLowerCase().toCharArray();

      Arrays.sort(wordArray);

      return new String(wordArray);
   }
   
   
}
