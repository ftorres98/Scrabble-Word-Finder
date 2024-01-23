import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Contains the main method. User either types a filename or gets assigned to sowpods.txt if one is not provided.
 * Then the user enters a rack and finds the words that can be made with the letters along with their scores.
 */

public class WordFinder {
   public static void main(String[] args) {
      String fileName = "";

      if(args.length == 0){
         fileName = "sowpods.txt";
      }
      else{
         fileName = args[0];
      }

      Scanner in = new Scanner(System.in);

      try{
         AnagramDictionary anagramDict = new AnagramDictionary(fileName);

         System.out.println("Type . to quit.");

         System.out.print("Rack? ");

         String rack = in.next();

         while(!rack.equals(".")){
            findWords(rack,anagramDict);

            System.out.print("Rack? ");
            rack = in.next();
         }
      }
      catch (IllegalDictionaryException e){
         System.out.println(e.getMessage());
         System.out.println("Exiting program.");
      }
      catch (FileNotFoundException e){
         System.out.println("ERROR: Dictionary file " + "\"" + fileName + "\" does not exist." );
         System.out.println("Exiting program.");
      }

   }

   /**
    * findWords creates a map of words with their score that can be made out from the rack and prints them using the
    * printWords method.
    * @param str the rack
    * @param anagramDict the AnagramDictionary that is created from the dictionary txt file
    */
   private static void findWords(String str, AnagramDictionary anagramDict){
      Rack currRack = new Rack(str);
      ArrayList<String> subsets = currRack.findAllSubsets();

      Map<String, Integer> words = new TreeMap<String, Integer>();

      for(int i = 1; i < subsets.size(); i++){
         ArrayList<String> anagrams = anagramDict.getAnagramsOf(subsets.get(i));
         if(anagrams != null){
            for(int j =0; j< anagrams.size();j++){
               ScoreTable score = new ScoreTable(anagrams.get(j));
               int num = score.getScore();
               words.put(anagrams.get(j), num);
            }
         }
      }

      printWords(words,str);
   }

   /**
    * prints the words with their score sorted by score
    * @param words a map with a key of type string and a value of int which is the score
    * @param str the rack
    */
   private static void printWords(Map<String, Integer> words, String str){
      System.out.println("We can make " + words.size() + " words from \"" + str + "\"");

      if(words.size() != 0) {
         ArrayList<Map.Entry<String, Integer>> listOfEntry = new ArrayList<Map.Entry<String, Integer>>(words.entrySet());

         listOfEntry.sort(new scoreSort());

         System.out.println("All of the words with their scores (sorted by score):");

         for (Map.Entry<String, Integer> entry : listOfEntry) {
            if(Character.isUpperCase(str.charAt(0))) {
               System.out.println(entry.getValue() + ": " + entry.getKey().toUpperCase());
            }
            else{
               System.out.println(entry.getValue() + ": " + entry.getKey());
            }
         }
      }

   }
}

class scoreSort implements Comparator<Map.Entry<String, Integer>> {

   public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
      return b.getValue() - a.getValue();
   }

}
