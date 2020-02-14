import java.util.*;
import java.io.*;

public class UfoPrinter {
	public static void main(String[] args) throws IOException {
	   List<String> frames = UfoPrinter.ufoFrames();
		File f1 = new File("nouns.txt");
		Scanner inputFile = new Scanner(f1);
	   Scanner scan = new Scanner(System.in);
		Random r1 = new Random();
      String playInput;
      boolean playAgain = true;
	   	    		
		// start game loop
      while(playAgain){
   		int currFrame = 0;
         String codeword = "", guess = "", incorrectGuesses = "";
         boolean solved = false;
         ArrayList<String> currGuess = new ArrayList<>();
         ArrayList<String> guessedLetters = new ArrayList<>();
   
   		// get the code word from nouns.txt
   		codeword = getCodeword(inputFile, 1, r1.nextInt(4300));
         for(int c = 0; c < codeword.length(); c++){
            currGuess.add("_");
         }
         // game instructions
         System.out.println("UFO: The Game \nInstructions: Save us from alien abduction by guessing letters in the codeword");
         System.out.println(frames.get(currFrame));
         
         // start loop in specific game
         while(currFrame < 6 && !solved){
      	    // incorrect Guesses
             printIncorrectGuesses(incorrectGuesses);
             // print out the progress
             printProgress(currGuess);
      	    // enter the guess, call to a method that checks it, change it to uppercase
      	    System.out.print("\n\nPlease enter your guess: ");            
             guess = checkGuess(guess, guessedLetters);
             // add letter to guessed letters
      	    guessedLetters.add(guess);
             
             // if the codeword contains guess
             if(codeword.contains(guess)){
               ArrayList<Integer> positions = getPositions(guess, codeword);
               // place the guess letters in the currGuess
               while(!positions.isEmpty()){
                  Integer temp = positions.remove(0);
                  currGuess.set(temp, guess);
               }
               // if the word is all guessed              
               if(!currGuess.contains("_")){
                  solved = true;
                  break;
               }
               System.out.println("\nCorrect! You're closer to cracking the codeword.");
             }	    
      	    else{
               System.out.println("\nIncorrect! The tractor beam pulls the person in further.");
               currFrame++;
               incorrectGuesses+=(guess+" ");
             }
             // print out the spaceship
             System.out.println(frames.get(currFrame));       
      	}
         playAgain = gameResult(solved, codeword);
      }
   }
   public static ArrayList<Integer> getPositions(String guess, String codeword){
      ArrayList<Integer> pos = new ArrayList<>();
      int index = 0;
      for(Character c : codeword.toCharArray()){
         if(guess.equals(c.toString())){
            pos.add(index);
         }
         index++;
      }   
      return pos;      
   }
   // prints out the game result and returns whether the user wants to play again
   public static boolean gameResult(boolean solved, String codeword){
      Scanner scan = new Scanner(System.in);
      boolean playAgain = true;
      String playInput;
      if(solved){
         System.out.println("\nCorrect! You saved the person and earned a medal of honor!");
         System.out.println("The code word is: " + codeword + ".");
      }
      else{
         System.out.println("Sorry! You did not save the person in time!");
         System.out.println("The code word is: " + codeword + ".");
      }
      System.out.print("\nWould you like to play again? (Y/N)? ");
      playInput = scan.nextLine();
      playInput = checkPlayInput(playInput);
      if(playInput.equals("N")){
         System.out.println("\nGoodbye!");
         playAgain = false;
      }
      return playAgain;
   }
   // checks if the play input is correct
   public static String checkPlayInput(String pi){
      Scanner scan = new Scanner(System.in);
      pi = pi.toUpperCase();
      while(!pi.equals("Y") && !pi.equals("N")){
         System.out.println("\nPlease enter Y for Yes and N for No");
         pi = scan.nextLine();
         pi = pi.toUpperCase();
      }
      return pi;
   }
   // prints the current progress of the word
   public static void printProgress(ArrayList<String> cg){
      System.out.println("\nCodeword:");
      for(int i = 0; i < cg.size(); i++){
         System.out.print(cg.get(i) + " ");
      }
   }
   // checks if the guess is a correct format
   public static String checkGuess(String guess, ArrayList<String> guessedLetters){
      Scanner scan = new Scanner(System.in);
      guess = scan.nextLine();
      while(guess.length() > 1 || guess.equals(" ") || guessedLetters.contains(guess.toUpperCase())){
            if(guessedLetters.contains(guess.toUpperCase())){
               System.out.println("\nYou can only guess that letter once, please try again");
               guess = scan.nextLine(); 
               guess = guess.toUpperCase();          
            }
            else{
               System.out.println("\nI cannot understand your input. Please guess a single letter.");
               guess = scan.nextLine();
            }
          }
       return guess.toUpperCase();
   }
   // prints incorrect guesses
   public static void printIncorrectGuesses(String incorrectGuesses){
       if(incorrectGuesses.length()==0) 
       	System.out.println("Incorrect Guesses: None");
       else
       	System.out.println("Incorrect Guesses:\n" + incorrectGuesses);
   }
   // gets the codeword from the file
	public static String getCodeword(Scanner inputFile, int currLine, int rand) {
		String codeWord = "";
		while(inputFile.hasNext()) {
			if(currLine == rand) {
				 codeWord = inputFile.nextLine();	             
             break;
         }
			else{
            codeWord = inputFile.nextLine();
				currLine++;
         }
		}
      codeWord = codeWord.toUpperCase();
		return codeWord;
	}
   // makes the list of ufoFrames
	public static List<String> ufoFrames() {
		List<String> ufoFrames = new ArrayList<String>();
	    StringBuilder sb = new StringBuilder();
	
	    sb.append("                  .\n")
	      .append("                  |\n")
	      .append("               .-\"^\"-.\n")
	      .append("              /_....._\\\n")
	      .append("          .-\"`         `\"-.\n")
	      .append("         (  ooo  ooo  ooo  )\n")
	      .append("          '-.,_________,.-'\n")
	      .append("               /     \\\n")
	      .append("              /       \\\n")
	      .append("             /    O    \\\n")
	      .append("            /   --|--   \\\n")
	      .append("           /      |      \\\n")
	      .append("          /      / \\      \\\n");
	
	    ufoFrames.add(sb.toString());
	    
	    sb = new StringBuilder();
	    sb.append("                  .\n")
	      .append("                  |\n")
	      .append("               .-\"^\"-.\n")
	      .append("              /_....._\\\n")
	      .append("          .-\"`         `\"-.\n")
	      .append("         (  ooo  ooo  ooo  )\n")
	      .append("          '-.,_________,.-'\n")
	      .append("               /     \\\n")
	      .append("              /   O   \\\n")
	      .append("             /  --|--  \\\n")
	      .append("            /     |     \\\n")
	      .append("           /     / \\     \\\n")
	      .append("          /               \\\n");
	
	    ufoFrames.add(sb.toString());
	    
	    sb = new StringBuilder();
	    sb.append("                  .\n")
	      .append("                  |\n")
	      .append("               .-\"^\"-.\n")
	      .append("              /_....._\\\n")
	      .append("          .-\"`         `\"-.\n")
	      .append("         (  ooo  ooo  ooo  )\n")
	      .append("          '-.,_________,.-'\n")
	      .append("               /  O  \\\n")
	      .append("              / --|-- \\\n")
	      .append("             /    |    \\\n")
	      .append("            /    / \\    \\\n")
	      .append("           /             \\\n")
	      .append("          /               \\\n");
	
	    ufoFrames.add(sb.toString());
	    
	    sb = new StringBuilder();
	    sb.append("                  .\n")
	      .append("                  |\n")
	      .append("               .-\"^\"-.\n")
	      .append("              /_....._\\\n")
	      .append("          .-\"`         `\"-.\n")
	      .append("         (  ooo  ooo  ooo  )\n")
	      .append("          '-.,_________,.-'\n")
	      .append("               /--|--\\\n")
	      .append("              /   |   \\\n")
	      .append("             /   / \\   \\\n")
	      .append("            /           \\\n")
	      .append("           /             \\\n")
	      .append("          /               \\\n");
	
	    ufoFrames.add(sb.toString());
	    
	    sb = new StringBuilder();
	    sb.append("                  .\n")
	      .append("                  |\n")
	      .append("               .-\"^\"-.\n")
	      .append("              /_....._\\\n")
	      .append("          .-\"`         `\"-.\n")
	      .append("         (  ooo  ooo  ooo  )\n")
	      .append("          '-.,_________,.-'\n")
	      .append("               /  |  \\\n")
	      .append("              /  / \\  \\\n")
	      .append("             /         \\\n")
	      .append("            /           \\\n")
	      .append("           /             \\\n")
	      .append("          /               \\\n");
	
	    ufoFrames.add(sb.toString());
	    
	    sb = new StringBuilder();
	    sb.append("                  .\n")
	      .append("                  |\n")
	      .append("               .-\"^\"-.\n")
	      .append("              /_....._\\\n")
	      .append("          .-\"`         `\"-.\n")
	      .append("         (  ooo  ooo  ooo  )\n")
	      .append("          '-.,_________,.-'\n")
	      .append("               / / \\ \\\n")
	      .append("              /       \\\n")
	      .append("             /         \\\n")
	      .append("            /           \\\n")
	      .append("           /             \\\n")
	      .append("          /               \\\n");
	
	
	    ufoFrames.add(sb.toString());
	    
	    sb = new StringBuilder();
	    sb.append("                  .\n")
	      .append("                  |\n")
	      .append("               .-\"^\"-.\n")
	      .append("              /_....._\\\n")
	      .append("          .-\"`         `\"-.\n")
	      .append("         (  ooo  ooo  ooo  )\n")
	      .append("          '-.,_________,.-'\n")
	      .append("               /     \\\n")
	      .append("              /       \\\n")
	      .append("             /         \\\n")
	      .append("            /           \\\n")
	      .append("           /             \\\n")
	      .append("          /               \\\n");
	
	    ufoFrames.add(sb.toString());
	
	    return ufoFrames;
	}
}
