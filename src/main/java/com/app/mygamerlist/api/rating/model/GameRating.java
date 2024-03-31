package com.app.mygamerlist.api.rating.model;

import com.app.mygamerlist.api.game.model.Game;


public class GameRating extends Rating {
    private final Game game;

    public GameRating(Game game) {
        super();
        this.game = game;
    }

    @Override
    public String toString() {
        return game + ", Rating: " + super.getRating();
    }
}
