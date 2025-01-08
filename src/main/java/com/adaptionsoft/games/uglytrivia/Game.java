package com.adaptionsoft.games.uglytrivia;

public class Game {
	private final Questions questions = new Questions();
	private final Players players = new Players();
    // 3 methods
	int[] places = new int[6];
    // 3 methods
	private int[] purses  = new int[6];
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
		getPurses()[players.size()] = 0;
		inPenaltyBox[players.size()] = false;
	    
	    System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	// 1 ref

	// 4+ ref
	public void roll(int roll) {
		System.out.println(players.getCurrentPlayerName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (inPenaltyBox[players.getCurrentPlayer()]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(players.getCurrentPlayerName() + " is getting out of the penalty box");
				places[players.getCurrentPlayer()] = places[players.getCurrentPlayer()] + roll;
				if (places[players.getCurrentPlayer()] > 11) places[players.getCurrentPlayer()] = places[players.getCurrentPlayer()] - 12;

				System.out.println(players.getCurrentPlayerName()
						+ "'s new location is " 
						+ places[players.getCurrentPlayer()]);
				System.out.println("The category is " + currentCategory());
				questions.askQuestion(currentCategory());
			} else {
				System.out.println(players.getCurrentPlayerName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {

			places[players.getCurrentPlayer()] = places[players.getCurrentPlayer()] + roll;
			if (places[players.getCurrentPlayer()] > 11) places[players.getCurrentPlayer()] = places[players.getCurrentPlayer()] - 12;

			System.out.println(players.getCurrentPlayerName()
					+ "'s new location is " 
					+ places[players.getCurrentPlayer()]);
			System.out.println("The category is " + currentCategory());
			questions.askQuestion(currentCategory());
		}
		
	}

	// 2 refs.
    private String currentCategory() {
		if (places[players.getCurrentPlayer()] == 0) return "Pop";
		if (places[players.getCurrentPlayer()] == 4) return "Pop";
		if (places[players.getCurrentPlayer()] == 8) return "Pop";
		if (places[players.getCurrentPlayer()] == 1) return "Science";
		if (places[players.getCurrentPlayer()] == 5) return "Science";
		if (places[players.getCurrentPlayer()] == 9) return "Science";
		if (places[players.getCurrentPlayer()] == 2) return "Sports";
		if (places[players.getCurrentPlayer()] == 6) return "Sports";
		if (places[players.getCurrentPlayer()] == 10) return "Sports";
		return "Rock";
	}

	// 4+ refs.
	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[players.getCurrentPlayer()]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				getPurses()[players.getCurrentPlayer()]++;
				System.out.println(players.getCurrentPlayerName()
						+ " now has "
						+ getPurses()[players.getCurrentPlayer()]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				players.setNextPlayer();

				return winner;
			} else {
				players.setNextPlayer();
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was correct!!!!");
			getPurses()[players.getCurrentPlayer()]++;
			System.out.println(players.getCurrentPlayerName()
					+ " now has "
					+ getPurses()[players.getCurrentPlayer()]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			players.setNextPlayer();

			return winner;
		}
	}

	// 4 refs.
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.getCurrentPlayerName()+ " was sent to the penalty box");
		inPenaltyBox[players.getCurrentPlayer()] = true;

		players.setNextPlayer();
		return true;
	}


	// 2 refs.
	private boolean didPlayerWin() {
		return !(getPurses()[players.getCurrentPlayer()] == 6);
	}

	public int[] getPurses() {
		return purses;
	}

}
