package com.app.mygamerlist.api.game.service;

import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.game.repository.GameRepository;
import com.app.mygamerlist.common.exception.IdMismatchException;
import com.app.mygamerlist.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Iterable<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public List<Game> findGameByTitle(String gameTitle) {
        return null;
    }

    @Override
    public Game findGameById(Long id) {
        return gameRepository.findById(id).orElseThrow();
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Game updateGame(Long id, Game game) {
        if (game.getId() != id) {
            throw new IdMismatchException();
        }
        gameRepository.findById(id)
                .orElseThrow();
        return gameRepository.save(game);
    }


}
