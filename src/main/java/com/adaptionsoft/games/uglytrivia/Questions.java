package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Questions {

    private final LinkedList<String> popQuestions = new LinkedList<>();
    private final LinkedList<String> scienceQuestions = new LinkedList<>();
    private final LinkedList<String> sportsQuestions = new LinkedList<>();
    private final LinkedList<String> rockQuestions = new LinkedList<>();


    // 4 ref. + 1 meth. ref.
    void askQuestion(String category) {
        if (category.equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (category.equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (category.equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (category.equals("Rock"))
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