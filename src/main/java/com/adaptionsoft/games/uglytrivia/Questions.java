package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.LinkedList;

public class Questions {
    private final Game game;
    private final PrintStream printStream;

    private final LinkedList<String> popQuestions = new LinkedList<>();
    private final LinkedList<String> scienceQuestions = new LinkedList<>();
    private final LinkedList<String> sportsQuestions = new LinkedList<>();
    private final LinkedList<String> rockQuestions = new LinkedList<>();

    public Questions(Game game, PrintStream printStream) {
        this.game = game;
        this.printStream = printStream;
    }

    // 5 fields questions and printStream + currentCategory()
    void askQuestion() {
        if (game.currentCategory().equals("Pop"))
            printStream.println(popQuestions.removeFirst());
        if (game.currentCategory().equals("Science"))
            printStream.println(scienceQuestions.removeFirst());
        if (game.currentCategory().equals("Sports"))
            printStream.println(sportsQuestions.removeFirst());
        if (game.currentCategory().equals("Rock"))
            printStream.println(rockQuestions.removeFirst());
    }

    public LinkedList<String> getPopQuestions() {
        return popQuestions;
    }

    public LinkedList<String> getScienceQuestions() {
        return scienceQuestions;
    }

    public LinkedList<String> getSportsQuestions() {
        return sportsQuestions;
    }

    public LinkedList<String> getRockQuestions() {
        return rockQuestions;
    }
}