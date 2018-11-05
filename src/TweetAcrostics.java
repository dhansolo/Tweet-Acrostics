import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class TweetAcrostics {
   public static void main (String[] args) throws FileNotFoundException {
      File tweets = new File("bin/tweets.txt");
      File dict = new File("bin/words-lowercase.txt");
      
      // Pre-process entire dictionary
      List<String> words = processDictionary(dict);
      
      // Process tweets to find acrostics
      processTweets(tweets, words);
   }
   
   // Pre: Valid file exists and is passed in, throws FileNotFoundException if not
   // Pre: Valid List<String> object passed in
   // Post: Prints out tweet along with corresponding acrostic and total acrostics found
   public static void processTweets(File f, List<String> words) throws FileNotFoundException {
      Scanner input = new Scanner(f);
      int count = 0;
      while(input.hasNextLine()) {
		String firstLetters = ""; 
		String line = input.nextLine(); 
		Scanner tokenScan = new Scanner(line); 
		while(tokenScan.hasNext()) {
		   String token = tokenScan.next().toLowerCase(); 
		   if(Character.isLetter(token.charAt(0))) {
		      firstLetters += (token.charAt(0) + "") ;
		   } else {
		      firstLetters = "";
		      break; 
		   }
		}
		if(firstLetters.length() >= 4) {
		   int index = Collections.binarySearch(words, firstLetters);
		   if(index >= 0) { 
		      count++;
		      System.out.println(line + " - " + firstLetters);
		   }
		}    
      }
      System.out.println();
      System.out.println(count + " acrostics found.");
   }
   
   // Pre: Valid file exists and is passed in, throws FileNotFoundException if not
   // Post: Returns List<String> containing all words found
   public static List<String> processDictionary(File dict) throws FileNotFoundException {
      List<String> words = new ArrayList<String>(); 
      Scanner input = new Scanner(dict);
      while(input.hasNext()) {
         String word = input.next();
         words.add(word);
      }
      return words;
   }
}