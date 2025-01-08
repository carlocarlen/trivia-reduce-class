package com.adaptionsoft.games.uglytrivia;

public class Game {
	private final Questions questions = new Questions();
	private final Players players = new Players();
    // 3 methods
	int[] places = new int[6];
    // 3 methods
	int[] purses  = new int[6];
    // 4 methods
	boolean[] inPenaltyBox  = new boolean[6];

	// 2 methods
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			questions.getPopQuestions().addLast("Pop Question " + i);
			questions.getScienceQuestions().addLast(("Science Question " + i));
			questions.getSportsQuestions().addLast(("Sports Question " + i));
			questions.getRockQuestions().addLast(createRockQuestion(i));
    	}
    }

	// 0 ref.
	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}

	// 1 meth ref.
	public boolean isPlayable() {
		return (players.size() >= 2);
	}

	// 4 refs. + 1 meth. ref
	public boolean add(String playerName) {


		players.add(playerName);
		places[players.size()] = 0;
		purses[players.size()] = 0;
		inPenaltyBox[players.size()] = false;
	    
	    System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	// 1 ref

	// 4+ ref
	public void roll(int roll) {
		System.out.println(players.get(getCurrentPlayer()) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[getCurrentPlayer()]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(players.get(getCurrentPlayer()) + " is getting out of the penalty box");
				places[getCurrentPlayer()] = places[getCurrentPlayer()] + roll;
				if (places[getCurrentPlayer()] > 11) places[getCurrentPlayer()] = places[getCurrentPlayer()] - 12;

				System.out.println(players.get(getCurrentPlayer())
						+ "'s new location is " 
						+ places[getCurrentPlayer()]);
				System.out.println("The category is " + currentCategory());
				questions.askQuestion(currentCategory());
			} else {
				System.out.println(players.get(getCurrentPlayer()) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[getCurrentPlayer()] = places[getCurrentPlayer()] + roll;
			if (places[getCurrentPlayer()] > 11) places[getCurrentPlayer()] = places[getCurrentPlayer()] - 12;

			System.out.println(players.get(getCurrentPlayer())
					+ "'s new location is " 
					+ places[getCurrentPlayer()]);
			System.out.println("The category is " + currentCategory());
			questions.askQuestion(currentCategory());
		}
		
	}

	// 2 refs.
    private String currentCategory() {
		if (places[getCurrentPlayer()] == 0) return "Pop";
		if (places[getCurrentPlayer()] == 4) return "Pop";
		if (places[getCurrentPlayer()] == 8) return "Pop";
		if (places[getCurrentPlayer()] == 1) return "Science";
		if (places[getCurrentPlayer()] == 5) return "Science";
		if (places[getCurrentPlayer()] == 9) return "Science";
		if (places[getCurrentPlayer()] == 2) return "Sports";
		if (places[getCurrentPlayer()] == 6) return "Sports";
		if (places[getCurrentPlayer()] == 10) return "Sports";
		return "Rock";
	}

	// 4+ refs.
	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[getCurrentPlayer()]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[getCurrentPlayer()]++;
				System.out.println(players.get(getCurrentPlayer())
						+ " now has "
						+ purses[getCurrentPlayer()]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				setCurrentPlayer(getCurrentPlayer() + 1);
				if (getCurrentPlayer() == players.size()) setCurrentPlayer(0);
				
				return winner;
			} else {
				setCurrentPlayer(getCurrentPlayer() + 1);
				if (getCurrentPlayer() == players.size()) setCurrentPlayer(0);
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was correct!!!!");
			purses[getCurrentPlayer()]++;
			System.out.println(players.get(getCurrentPlayer())
					+ " now has "
					+ purses[getCurrentPlayer()]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			setCurrentPlayer(getCurrentPlayer() + 1);
			if (getCurrentPlayer() == players.size()) setCurrentPlayer(0);
			
			return winner;
		}
	}

	// 4 refs.
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(getCurrentPlayer())+ " was sent to the penalty box");
		inPenaltyBox[getCurrentPlayer()] = true;
		
		setCurrentPlayer(getCurrentPlayer() + 1);
		if (getCurrentPlayer() == players.size()) setCurrentPlayer(0);
		return true;
	}


	// 2 refs.
	private boolean didPlayerWin() {
		return !(purses[getCurrentPlayer()] == 6);
	}

	public int getCurrentPlayer() {
		return players.getCurrentPlayer();
	}

	public void setCurrentPlayer(int currentPlayer) {
		players.setCurrentPlayer(currentPlayer);
	}
}
