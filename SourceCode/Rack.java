import java.util.ArrayList;
import java.util.Arrays;

/**
   A Rack of Scrabble tiles
 */

public class Rack {

   /**
    Representation invariant:

    -- currRack is NEVER null

    */

   private String currRack;

   /**
    * Creates a Rack object with a string parameter.
    * @param str takes in the string and assigns it currRack
    */
   public Rack(String str){
      currRack = str;

   }

   /**
    * The findAllSubsets method takes the currRack and creates a string with unique characters
    * and an array of type int which the multiplicity of each character.
    * @return all subsets of the currRack
    */
   public ArrayList<String> findAllSubsets(){
      String sortedWord = sortedForm(currRack);
      String unique = "";

      for(int i = 0; i<sortedWord.length(); i++){
         if( i == 0){
            unique += sortedWord.charAt(i);
         }
         else{
            if(sortedWord.charAt(i-1) != sortedWord.charAt(i)){
               unique += sortedWord.charAt(i);
            }
         }
      }

      int[] mult = new int[unique.length()];

      for(int i = 0; i < mult.length; i++){
         int count = 0;
         for(int j = 0; j<sortedWord.length(); j++){
            if(sortedWord.charAt(j) == unique.charAt(i)){
               count++;
            }
         }
         mult[i] = count;
      }

      return allSubsets(unique, mult, 0);
   }


   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   /**
    * This method takes in a string and sorts the characters in alphabetical order
    * and returns the resulting string.
    * @param word string to be sorted
    * @return a string in which the characters are in alphabetical order
    */
   private String sortedForm(String word){
      char [] wordArray = word.toLowerCase().toCharArray();

      Arrays.sort(wordArray);

      return new String(wordArray);
   }

   
}
