package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.LinkedList;

public class Game {
	
	private final PrintStream printStream;
	
	private final Players players = new Players();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList<String> popQuestions = new LinkedList<>();
	LinkedList<String> scienceQuestions = new LinkedList<>();
	LinkedList<String> sportsQuestions = new LinkedList<>();
	LinkedList<String> rockQuestions = new LinkedList<>();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(PrintStream printStream){
        this.printStream = printStream;
        for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {


		players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    printStream.println(playerName + " was added");
		printStream.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

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
				askQuestion();
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
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory().equals("Pop"))
			printStream.println(popQuestions.removeFirst());
		if (currentCategory().equals("Science"))
			printStream.println(scienceQuestions.removeFirst());
		if (currentCategory().equals("Sports"))
			printStream.println(sportsQuestions.removeFirst());
		if (currentCategory().equals("Rock"))
			printStream.println(rockQuestions.removeFirst());		
	}
	
	
	private String currentCategory() {
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
	
	public boolean wrongAnswer(){
		printStream.println("Question was incorrectly answered");
		printStream.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}

}
