package com.adaptionsoft.games.uglytrivia;

public class Questions {
    private final Game game;

    public Questions(Game game) {
        this.game = game;
    }

    // 4 ref. + 1 meth. ref.
    void askQuestion() {
        if (game.currentCategory().equals("Pop"))
            System.out.println(game.getPopQuestions().removeFirst());
        if (game.currentCategory().equals("Science"))
            System.out.println(game.getScienceQuestions().removeFirst());
        if (game.currentCategory().equals("Sports"))
            System.out.println(game.getSportsQuestions().removeFirst());
        if (game.currentCategory().equals("Rock"))
            System.out.println(game.getRockQuestions().removeFirst());
    }
}