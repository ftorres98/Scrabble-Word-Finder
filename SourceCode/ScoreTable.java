import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class assigns a score to a word.
 */

public class ScoreTable {

   private static final int scoreOf1 = 1;
   private static final int scoreOf2 = 2;
   private static final int scoreOf3 = 3;
   private static final int scoreOf4 = 4;
   private static final int scoreOf5 = 5;
   private static final int scoreOf8 = 8;
   private static final int scoreOf10 = 10;

   private static final Set<Character> AEIOULNSTR = new HashSet<Character>(Arrays.asList('a','e','i','o','u','l','n','s','t','r'));
   private static final Set<Character> DG = new HashSet<Character>(Arrays.asList('d','g'));
   private static final Set<Character> BCMP = new HashSet<Character>(Arrays.asList('b','c','m','p'));
   private static final Set<Character> FHVWY = new HashSet<Character>(Arrays.asList('f','h','v','w','y'));
   private static final Set<Character> JX = new HashSet<Character>(Arrays.asList('j','x'));
   private static final Set<Character> QZ = new HashSet<Character>(Arrays.asList('q','z'));

   /**
    Representation invariant:

    -- word is NEVER null

    */

   private String word;

   /**
    * Creates an object of ScoreTable with a String parameter
    * @param str assigned to the String word
    */
   public ScoreTable(String str){
      word = str.toLowerCase();
   }

   /**
    * getScore takes the String word and traverses through it to calculate the score.
    * @return the score for the String word
    */
   public int getScore(){
      int count = 0;

      for(int i =0; i < word.length(); i++){
         if(AEIOULNSTR.contains(word.charAt(i))){
            count += scoreOf1;
         }
         else if(DG.contains(word.charAt(i))){
            count += scoreOf2;
         }
         else if(BCMP.contains(word.charAt(i))){
            count += scoreOf3;
         }
         else if(FHVWY.contains(word.charAt(i))){
            count += scoreOf4;
         }
         else if(JX.contains(word.charAt(i))){
            count += scoreOf8;
         }
         else if(QZ.contains(word.charAt(i))){
            count += scoreOf10;
         }
         //else will be for when the char is 'k'
         else{
            count += scoreOf5;
         }
      }
      return count;
   }
}
