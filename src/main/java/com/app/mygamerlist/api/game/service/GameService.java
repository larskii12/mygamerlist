package com.app.mygamerlist.api.game.service;

import com.app.mygamerlist.api.game.model.Game;

import java.util.List;

public interface GameService {

    Iterable<Game> findAll();

    List<Game> findGameByTitle(String gameTitle);

    Game findGameById(Long id);

    Game saveGame(Game game);

    void deleteById(Long id);

    Game updateGame(Long id, Game game);
}
