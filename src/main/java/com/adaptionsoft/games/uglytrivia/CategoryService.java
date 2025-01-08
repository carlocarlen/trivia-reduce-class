package com.adaptionsoft.games.uglytrivia;

public class CategoryService {
    private final Game game;

    public CategoryService(Game game) {
        this.game = game;
    }

    // 2 refs.
    String currentCategory() {
        if (game.getPlaces()[game.getCurrentPlayer()] == 0) return "Pop";
        if (game.getPlaces()[game.getCurrentPlayer()] == 4) return "Pop";
        if (game.getPlaces()[game.getCurrentPlayer()] == 8) return "Pop";
        if (game.getPlaces()[game.getCurrentPlayer()] == 1) return "Science";
        if (game.getPlaces()[game.getCurrentPlayer()] == 5) return "Science";
        if (game.getPlaces()[game.getCurrentPlayer()] == 9) return "Science";
        if (game.getPlaces()[game.getCurrentPlayer()] == 2) return "Sports";
        if (game.getPlaces()[game.getCurrentPlayer()] == 6) return "Sports";
        if (game.getPlaces()[game.getCurrentPlayer()] == 10) return "Sports";
        return "Rock";
    }
}