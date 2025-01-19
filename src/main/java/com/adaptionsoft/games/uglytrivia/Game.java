package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {

	// 6 methods
	private final PrintStream printStream;
	// 2 methods: constructor and roll
    private final Questions questions;

    // 5 methods
    ArrayList<String> players = new ArrayList<>();
	// 3 methods: add, roll, currentCategory
    int[] places = new int[6];
	// 3 methods: add, wasCorrectlyAnswered, didPlayerWin
    int[] purses  = new int[6];
	// 4 methods
    boolean[] inPenaltyBox  = new boolean[6];

	// 5 methods
    int currentPlayer = 0;
	// 2 methods: roll, wasCorrectlyAnswered
    boolean isGettingOutOfPenaltyBox;

    public  Game(PrintStream printStream){
        this.printStream = printStream;
		this.questions = new Questions(this, printStream);
        for (int i = 0; i < 50; i++) {
			getPopQuestions().addLast("Pop Question " + i);
			getScienceQuestions().addLast(("Science Question " + i));
			getSportsQuestions().addLast(("Sports Question " + i));
			getRockQuestions().addLast(createRockQuestion(i));
    	}
    }

	// 0 references
	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}

	// 1 method
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	// 5 fields + howManyPlayers
	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    printStream.println(playerName + " was added");
	    printStream.println("They are player number " + players.size());
		return true;
	}

	// 1 field
	public int howManyPlayers() {
		return players.size();
	}

	// A lot
	public void roll(int roll) {
		printStream.println(players.get(currentPlayer) + " is the current player");
		printStream.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				printStream.println(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				printStream.println(players.get(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
				printStream.println("The category is " + currentCategory());
                questions.askQuestion();
			} else {
				printStream.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			
			printStream.println(players.get(currentPlayer) 
					+ "'s new location is " 
					+ places[currentPlayer]);
			printStream.println("The category is " + currentCategory());
            questions.askQuestion();
		}
		
	}
	
	// 2 fields
    String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
	}

	// A lot
	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				printStream.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				printStream.println(players.get(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			printStream.println("Answer was correct!!!!");
			purses[currentPlayer]++;
			printStream.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}

	// 4 fields
	public boolean wrongAnswer(){
		printStream.println("Question was incorrectly answered");
		printStream.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}
	
	// 2 fields
	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}

	public LinkedList<String> getPopQuestions() {
		return questions.getPopQuestions();
	}

	public LinkedList<String> getScienceQuestions() {
		return questions.getScienceQuestions();
	}

	public LinkedList<String> getSportsQuestions() {
		return questions.getSportsQuestions();
	}

	public LinkedList<String> getRockQuestions() {
		return questions.getRockQuestions();
	}

}
