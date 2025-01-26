package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<String> players = new ArrayList<>();
    private int currentPlayer = 0;

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void add(String playerName) {
        players.add(playerName);
    }

    public int size() {
        return players.size();
    }

    public String getCurrentPlayerName() {
        return players.get(currentPlayer);
    }

    public void setNextPlayer() {
        setCurrentPlayer(getCurrentPlayer() + 1);
        if (getCurrentPlayer() == size()) setCurrentPlayer(0);
    }
}
