package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Questions {
    private final Game game;

    private final LinkedList<String> popQuestions = new LinkedList<>();
    private final LinkedList<String> scienceQuestions = new LinkedList<>();
    private final LinkedList<String> sportsQuestions = new LinkedList<>();
    private final LinkedList<String> rockQuestions = new LinkedList<>();

    public Questions(Game game) {
        this.game = game;
    }

    // 4 ref. + 1 meth. ref.
    void askQuestion() {
        if (game.currentCategory().equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (game.currentCategory().equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (game.currentCategory().equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (game.currentCategory().equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
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