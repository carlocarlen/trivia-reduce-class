package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;

public class Questions {
    private final Game game;
    private final PrintStream printStream;

    public Questions(Game game, PrintStream printStream) {
        this.game = game;
        this.printStream = printStream;
    }

    // 5 fields questions and printStream + currentCategory()
    void askQuestion() {
        if (game.currentCategory().equals("Pop"))
            printStream.println(game.getPopQuestions().removeFirst());
        if (game.currentCategory().equals("Science"))
            printStream.println(game.getScienceQuestions().removeFirst());
        if (game.currentCategory().equals("Sports"))
            printStream.println(game.getSportsQuestions().removeFirst());
        if (game.currentCategory().equals("Rock"))
            printStream.println(game.getRockQuestions().removeFirst());
    }
}