package com.app.mygamerlist.api.game.repository;

import com.app.mygamerlist.api.game.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long>{
    List<Game> findByTitle(String title);
}
