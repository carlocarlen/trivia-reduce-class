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
            System.out.println(game.getPopQuestions().removeFirst());
        if (game.currentCategory().equals("Science"))
            System.out.println(game.getScienceQuestions().removeFirst());
        if (game.currentCategory().equals("Sports"))
            System.out.println(game.getSportsQuestions().removeFirst());
        if (game.currentCategory().equals("Rock"))
            System.out.println(game.getRockQuestions().removeFirst());
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