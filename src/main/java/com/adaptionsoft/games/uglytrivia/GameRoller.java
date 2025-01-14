package com.adaptionsoft.games.uglytrivia;

public class GameRoller {
    private final Game game;

    public GameRoller(Game game) {
        this.game = game;
    }

    public void roll(int roll) {
        game.getPrintStream().println(game.getPlayers().get(game.getCurrentPlayer()) + " is the current player");
        game.getPrintStream().println("They have rolled a " + roll);

        if (game.getInPenaltyBox()[game.getCurrentPlayer()]) {
            if (roll % 2 != 0) {
                game.setGettingOutOfPenaltyBox(true);

                game.getPrintStream().println(game.getPlayers().get(game.getCurrentPlayer()) + " is getting out of the penalty box");
                game.getPlaces()[game.getCurrentPlayer()] = game.getPlaces()[game.getCurrentPlayer()] + roll;
                if (game.getPlaces()[game.getCurrentPlayer()] > 11)
                    game.getPlaces()[game.getCurrentPlayer()] = game.getPlaces()[game.getCurrentPlayer()] - 12;

                game.getPrintStream().println(game.getPlayers().get(game.getCurrentPlayer())
                        + "'s new location is "
                        + game.getPlaces()[game.getCurrentPlayer()]);
                game.getPrintStream().println("The category is " + game.currentCategory());
                game.askQuestion();
            } else {
                game.getPrintStream().println(game.getPlayers().get(game.getCurrentPlayer()) + " is not getting out of the penalty box");
                game.setGettingOutOfPenaltyBox(false);
            }

        } else {

            game.getPlaces()[game.getCurrentPlayer()] = game.getPlaces()[game.getCurrentPlayer()] + roll;
            if (game.getPlaces()[game.getCurrentPlayer()] > 11)
                game.getPlaces()[game.getCurrentPlayer()] = game.getPlaces()[game.getCurrentPlayer()] - 12;

            game.getPrintStream().println(game.getPlayers().get(game.getCurrentPlayer())
                    + "'s new location is "
                    + game.getPlaces()[game.getCurrentPlayer()]);
            game.getPrintStream().println("The category is " + game.currentCategory());
            game.askQuestion();
        }

    }
}