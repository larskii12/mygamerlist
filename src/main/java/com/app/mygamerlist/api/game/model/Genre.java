package com.app.mygamerlist.api.game.model;

public enum Genre {
    ADVENTURE("adventure"),
    ACTION("action"),
    SPORTS("sports"),
    SIMULATION("simulation"),
    PLATFORMER("platformer"),
    RPG("role-playing"),
    FPS("first-person-shooter"),
    ACTION_ADVENTURE("action-adventure"),
    FIGHTING("fighting"),
    RTS("real-time-strategy"),
    RACING("racing"),
    SHOOTER("shooter"),
    PUZZLE("puzzle"),
    CASUAL("casual"),
    STRATEGY("strategy"),
    MMORPG("massive-multiplayer-online-role-playing"),
    STEALTH("stealth"),
    PARTY("party"),
    ACTION_RPG("action-rpg"),
    TACTICAL_RPG("tactical-rpg"),
    SURVIVAL("survival"),
    BATTLE_ROYALE("battle-royale");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
