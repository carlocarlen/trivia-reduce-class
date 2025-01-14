package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	
	private final PrintStream printStream;
	
    private final ArrayList<String> players = new ArrayList<>();
    private final int[] places = new int[6];
    int[] purses  = new int[6];
    private final boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList<String> popQuestions = new LinkedList<>();
	LinkedList<String> scienceQuestions = new LinkedList<>();
	LinkedList<String> sportsQuestions = new LinkedList<>();
	LinkedList<String> rockQuestions = new LinkedList<>();
    
    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;
    
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
		
		
	    getPlayers().add(playerName);
	    getPlaces()[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    getInPenaltyBox()[howManyPlayers()] = false;
	    
	    getPrintStream().println(playerName + " was added");
	    getPrintStream().println("They are player number " + getPlayers().size());
		return true;
	}
	
	public int howManyPlayers() {
		return getPlayers().size();
	}

	public void roll(int roll) {
		getPrintStream().println(getPlayers().get(getCurrentPlayer()) + " is the current player");
		getPrintStream().println("They have rolled a " + roll);
		
		if (getInPenaltyBox()[getCurrentPlayer()]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				getPrintStream().println(getPlayers().get(getCurrentPlayer()) + " is getting out of the penalty box");
				getPlaces()[getCurrentPlayer()] = getPlaces()[getCurrentPlayer()] + roll;
				if (getPlaces()[getCurrentPlayer()] > 11) getPlaces()[getCurrentPlayer()] = getPlaces()[getCurrentPlayer()] - 12;
				
				getPrintStream().println(getPlayers().get(getCurrentPlayer())
						+ "'s new location is " 
						+ getPlaces()[getCurrentPlayer()]);
				getPrintStream().println("The category is " + currentCategory());
				askQuestion();
			} else {
				getPrintStream().println(getPlayers().get(getCurrentPlayer()) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			getPlaces()[getCurrentPlayer()] = getPlaces()[getCurrentPlayer()] + roll;
			if (getPlaces()[getCurrentPlayer()] > 11) getPlaces()[getCurrentPlayer()] = getPlaces()[getCurrentPlayer()] - 12;
			
			getPrintStream().println(getPlayers().get(getCurrentPlayer())
					+ "'s new location is " 
					+ getPlaces()[getCurrentPlayer()]);
			getPrintStream().println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory().equals("Pop"))
			getPrintStream().println(popQuestions.removeFirst());
		if (currentCategory().equals("Science"))
			getPrintStream().println(scienceQuestions.removeFirst());
		if (currentCategory().equals("Sports"))
			getPrintStream().println(sportsQuestions.removeFirst());
		if (currentCategory().equals("Rock"))
			getPrintStream().println(rockQuestions.removeFirst());
	}
	
	
	private String currentCategory() {
		if (getPlaces()[getCurrentPlayer()] == 0) return "Pop";
		if (getPlaces()[getCurrentPlayer()] == 4) return "Pop";
		if (getPlaces()[getCurrentPlayer()] == 8) return "Pop";
		if (getPlaces()[getCurrentPlayer()] == 1) return "Science";
		if (getPlaces()[getCurrentPlayer()] == 5) return "Science";
		if (getPlaces()[getCurrentPlayer()] == 9) return "Science";
		if (getPlaces()[getCurrentPlayer()] == 2) return "Sports";
		if (getPlaces()[getCurrentPlayer()] == 6) return "Sports";
		if (getPlaces()[getCurrentPlayer()] == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (getInPenaltyBox()[getCurrentPlayer()]){
			if (isGettingOutOfPenaltyBox()) {
				getPrintStream().println("Answer was correct!!!!");
				purses[getCurrentPlayer()]++;
				getPrintStream().println(getPlayers().get(getCurrentPlayer())
						+ " now has "
						+ purses[getCurrentPlayer()]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer = getCurrentPlayer() + 1;
				if (getCurrentPlayer() == getPlayers().size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer = getCurrentPlayer() + 1;
				if (getCurrentPlayer() == getPlayers().size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			getPrintStream().println("Answer was correct!!!!");
			purses[getCurrentPlayer()]++;
			getPrintStream().println(getPlayers().get(getCurrentPlayer())
					+ " now has "
					+ purses[getCurrentPlayer()]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer = getCurrentPlayer() + 1;
			if (getCurrentPlayer() == getPlayers().size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		getPrintStream().println("Question was incorrectly answered");
		getPrintStream().println(getPlayers().get(getCurrentPlayer())+ " was sent to the penalty box");
		getInPenaltyBox()[getCurrentPlayer()] = true;
		
		currentPlayer = getCurrentPlayer() + 1;
		if (getCurrentPlayer() == getPlayers().size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[getCurrentPlayer()] == 6);
	}

	public PrintStream getPrintStream() {
		return printStream;
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

	public int[] getPlaces() {
		return places;
	}

	public boolean[] getInPenaltyBox() {
		return inPenaltyBox;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean isGettingOutOfPenaltyBox() {
		return isGettingOutOfPenaltyBox;
	}
}
