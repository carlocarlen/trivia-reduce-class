package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<String> players = new ArrayList<>();

    public List<String> getPlayers() {
        return players;
    }

    public void add(String playerName) {
        players.add(playerName);
    }

}
