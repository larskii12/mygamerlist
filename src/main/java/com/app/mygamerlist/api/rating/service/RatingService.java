package com.app.mygamerlist.api.rating.service;

import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.rating.model.Rating;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface RatingService {

    Iterable<Rating> findAll();

    Iterable<Rating> findAllRatingsByGame(Long gameId, String title);

    Iterable<Rating> findAllRatingsByCharacter(Long characterId, String name);

    Iterable<Rating> findAllRatingsByUser(Long userId, String username);

    Rating findRatingById(Long id);

    Rating createRating(Rating rating);

    Rating updateRating(Long id, Rating rating);

    void deleteById(Long id);
}
