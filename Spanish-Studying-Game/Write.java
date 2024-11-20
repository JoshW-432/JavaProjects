// Copyright 2024 Joshua Williams
//

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;


public class Write {
	
	//class-wide variables
	private static ArrayList<Set> Sets = new ArrayList<Set>();
	private static Scanner input;
	private static boolean wantPlay;
	
	
	public static void playingGameES() {
		
		//list to hold generated numbers so they don't repeat
		ArrayList<Integer> usedNumbers = new ArrayList<Integer>();

		for (int i = 0; i < Sets.get(0).length(); ++i) {
			//creates instance of random class
			Random random = new Random();
			//generates random number
			int randomNumber = random.nextInt(Sets.get(0).length());
			//makes sure number isn't repeated
			while (isRepeated(usedNumbers, randomNumber)) {
				randomNumber = random.nextInt(Sets.get(0).length());
			}

			//prints spanish word
			System.out.print("\n" + Sets.get(0).getES(randomNumber) + "\n-> ");
			//initializes string to take in the guess
			String guess = "";
			//save the number of guesses so "try again" isn't printed the first time.
			int numGuess = 0;
			//repeats until a correct guess
			while (!guess.equals(Sets.get(0).getEN(randomNumber))) {
				//if the user guesses wrong then try again will be printed on subsequent attempts
				if (numGuess > 0) {
					System.out.println("Try again!" + " (" + numGuess + "/5)");
				}
				//if the user is struggling they get told the answer
				if (numGuess >= 5) {
					System.out.println("The answer is: " + Sets.get(0).getEN(randomNumber) + "\nType it in ->");
				}
				
				//takes in the users guess
				guess = fResponse();
				//increases the guesses
				++numGuess;
			}
			//adds number to the list that of used numbers
			usedNumbers.add(randomNumber);
			//response to the user guessing correctly
			System.out.println("You got it!");

		}
		
	}
	
	public static boolean isRepeated(ArrayList<Integer> usedNumbers, int randomNum) {
		//checks each item of list to see if it is repeated
		for (int i = 0; i < usedNumbers.size(); ++i) {
			//if it is repeated > return true
			if (usedNumbers.get(i) == randomNum) {
				return true;
			}
		}
		//otherwise > false
		return false;
		
	}
	
	public static void playingGameEN() {
		
		//list to hold generated numbers so they don't repeat
		ArrayList<Integer> usedNumbers = new ArrayList<Integer>();

		for (int i = 0; i < Sets.get(0).length(); ++i) {
			//creates instance of random class
			Random random = new Random();
			//generates random number
			int randomNumber = random.nextInt(Sets.get(0).length());
			//makes sure number isn't repeated
			while (isRepeated(usedNumbers, randomNumber)) {
				randomNumber = random.nextInt(Sets.get(0).length());
			}

			//prints english word
			System.out.print("\n" + Sets.get(0).getEN(randomNumber) + "\n-> ");
			//initializes string to take in the guess
			String guess = "";
			//save the number of guesses so "try again" isn't printed the first time.
			int numGuess = 0;
			//repeats until a correct guess
			while (!guess.equals(Sets.get(0).getES(randomNumber))) {
				//if the user guesses wrong then try again will be printed on subsequent attempts
				if (numGuess > 0) {
					System.out.println("Try again!" + " (" + numGuess + "/5)");
				}
				//if the user is struggling they get told the answer
				if (numGuess >= 5) {
					System.out.println("The answer is: " + Sets.get(0).getES(randomNumber) + "\nType it in ->");
				}
				//takes in the users guess
				guess = fResponse();
				//increases the guesses
				++numGuess;
			}
			//adds number to the list that of used numbers
			usedNumbers.add(randomNumber);
			//response to the user guessing correctly
			System.out.println("You got it!");


		}
		
	}
	
	public static void initiateGame() {
		//takes in the answer to if they want to play
		String response = fResponse();
		//if they did say yes the game begins
		if (response.equals("yes")) {
			//requests the user to choose a language to answer with
			System.out.print("Do you want to answer with Spanish or English?\n-> ");
			//takes in the input
			response = fResponse();
			//a couple options for what spanish could be
			if (response.equals("spanish") || response.equals("span") || response.equals("sp") || response.equals("es")) {
				//playing english to spanish
				System.out.print("Answering in Spanish!\n");
				playingGameES();
			} else { 
				//play the game Spanish to English
				System.out.print("Answering in English!\n");
				playingGameEN();
			}
			//again ?
			System.out.print("\nWant to play again?\n-> ");
			
		//if the user doesn't want to play again then the boolean is updated and the game ends
		} else {
			wantPlay = false;
		}
	}
	
	public static String fResponse() {
		//take in input
		String response = input.next();
		//convert to lower case
		response = response.toLowerCase();
		//return it
		return response;
	}
	
	public static String checkStop(String response) {
		while (response.equals("!stop!")) {
			System.out.print("invalid response\nType a different word\n-> ");
			response = fResponse();
		}
		return response;
	}
	
	public static void addWords(ArrayList<String> englishWords, ArrayList<String> spanishWords) {
		String response = "";
		// tells them how to quit adding
		System.out.print("\nType \"!stop!\" when you are done.");
		// if they type "!stop!" it ends
		while(!response.equals("!stop!")) {
			// requests english word
			System.out.print("\nType English word\n-> ");
			// takes in response
			response = fResponse();
			if (!response.equals("!stop!")) {
				// adds the english word to list
				englishWords.add(response);
				// requests spanish word
				System.out.print("\nType Spanish word\n-> ");
				// takes in response
				response = fResponse();
				// makes sure they don't type !stop!
				response = checkStop(response);
				// adds the spanish word to list
				spanishWords.add(response);
			}
		}
	}
	
	public static void main(String[] args) {

		//takes in input from the user in the console
		input = new Scanner(System.in);
		//creates two lists to hold the words 
		ArrayList<String> englishWords = new ArrayList<String>();
		ArrayList<String> spanishWords = new ArrayList<String>();
		//not sure why try is needed; TODO figure that out
		try {
			//makes the file readable by java
			File myFile = new File("TranslatedWords.txt");
			//scans the file
			Scanner reader = new Scanner(myFile);
			while (reader.hasNext()) {
				//adds to the english list
				englishWords.add(reader.next());
				//adds to the spanish list
				spanishWords.add(reader.next());
			}
			reader.close();
		// if there is an answer than an error is thrown
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();

		}
		
		//creates a set of the words
		Sets.add( new Set(englishWords, spanishWords));
		
		// asks if they want to play the game or add to the set
		System.out.print("Do you want to \"study\" or \"add\" words to the set?\n-> ");
		// takes in the response of if they want to add or study
		String response = fResponse();
		// if they want to add to it they type add
		if (response.equals("add")) {
			addWords(englishWords, spanishWords);
		}
		//instructions and consent
		System.out.print("\nThis is a study game where you translate words.\n"
				+ "Do you want to play? (If yes say \"yes\")\n-> ");
		//while the player wants to play the game is initiated
		wantPlay = true;
		while (wantPlay) {
			initiateGame();
		}
		//ending message
		System.out.println("\nThanks for playing! ");
	
	}

}
