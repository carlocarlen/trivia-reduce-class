package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	// 5 methods
    ArrayList<String> players = new ArrayList<>();
    // 3 methods
	int[] places = new int[6];
    // 3 methods
	int[] purses  = new int[6];
    // 4 methods
	boolean[] inPenaltyBox  = new boolean[6];

	// constructor + 1 method
	private LinkedList<String> popQuestions = new LinkedList<>();
	private LinkedList<String> scienceQuestions = new LinkedList<>();
	private LinkedList<String> sportsQuestions = new LinkedList<>();
	private LinkedList<String> rockQuestions = new LinkedList<>();

	// 5 methods
    int currentPlayer = 0;
	// 2 methods
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			getPopQuestions().addLast("Pop Question " + i);
			getScienceQuestions().addLast(("Science Question " + i));
			getSportsQuestions().addLast(("Sports Question " + i));
			getRockQuestions().addLast(createRockQuestion(i));
    	}
    }

	// 0 ref.
	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}

	// 1 meth ref.
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	// 4 refs. + 1 meth. ref
	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}

	// 1 ref
	public int howManyPlayers() {
		return players.size();
	}

	// 4+ ref
	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				System.out.println(players.get(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			
			System.out.println(players.get(currentPlayer) 
					+ "'s new location is " 
					+ places[currentPlayer]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	// 4 ref. + 1 meth. ref.
	private void askQuestion() {
		if (currentCategory().equals("Pop"))
			System.out.println(getPopQuestions().removeFirst());
		if (currentCategory().equals("Science"))
			System.out.println(getScienceQuestions().removeFirst());
		if (currentCategory().equals("Sports"))
			System.out.println(getSportsQuestions().removeFirst());
		if (currentCategory().equals("Rock"))
			System.out.println(getRockQuestions().removeFirst());
	}
	
	// 2 refs.
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

	// 4+ refs.
	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) 
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
		
			System.out.println("Answer was correct!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}

	// 4 refs.
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	// 2 refs.
	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}

	public LinkedList<String> getPopQuestions() {
		return popQuestions;
	}

	public void setPopQuestions(LinkedList<String> popQuestions) {
		this.popQuestions = popQuestions;
	}

	public LinkedList<String> getScienceQuestions() {
		return scienceQuestions;
	}

	public void setScienceQuestions(LinkedList<String> scienceQuestions) {
		this.scienceQuestions = scienceQuestions;
	}

	public LinkedList<String> getSportsQuestions() {
		return sportsQuestions;
	}

	public void setSportsQuestions(LinkedList<String> sportsQuestions) {
		this.sportsQuestions = sportsQuestions;
	}

	public LinkedList<String> getRockQuestions() {
		return rockQuestions;
	}

	public void setRockQuestions(LinkedList<String> rockQuestions) {
		this.rockQuestions = rockQuestions;
	}
}
