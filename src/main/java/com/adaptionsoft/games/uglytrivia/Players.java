package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<String> players = new ArrayList<>();

    public void add(String playerName) {
        players.add(playerName);
    }

    public int size() {
        return players.size();
    }

    public String get(int index) {
        return players.get(index);
    }

}
