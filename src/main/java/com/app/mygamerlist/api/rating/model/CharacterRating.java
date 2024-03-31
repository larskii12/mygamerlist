package com.app.mygamerlist.api.rating.model;

import com.app.mygamerlist.api.game.model.Game;

public class CharacterRating extends Rating {

    private final Character character;

    public CharacterRating(Character character) {
        super();
        this.character = character;
    }

    @Override
    public String toString() {
        return character + ", Rating: " + super.getRating();
    }
}
