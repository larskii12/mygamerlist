package com.app.mygamerlist.api.rating.controller;

import com.app.mygamerlist.api.character.model.Character;
import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.game.service.GameService;
import com.app.mygamerlist.api.rating.mapper.RatingMapper;
import com.app.mygamerlist.api.rating.model.Rating;
import com.app.mygamerlist.api.rating.model.RatingDto;
import com.app.mygamerlist.api.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingMapper ratingMapper;

    @GetMapping
    public Iterable<Rating> viewRatingList() {
        return ratingService.findAll();
    }

    @GetMapping("/games/{gameId}")
    public Iterable<Rating> viewRatingListByGame(@PathVariable Long gameId) {
        return ratingService.findAllRatingsByGame(gameId);
    }

    @GetMapping("/characters/{characterId}")
    public Iterable<Rating> viewRatingListByCharacter(@PathVariable Long characterId) {
        return ratingService.findAllRatingsByCharacter(characterId);
    }

    @GetMapping("/users/{userId}")
    public Iterable<Rating> viewRatingListByUser(@PathVariable Long userId) {
        return ratingService.findAllRatingsByUser(userId);
    }

    @GetMapping("/{id}")
    public Rating findRatingById(@PathVariable Long id) {
        return ratingService.findRatingById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.createRating(rating);
    }

    @PutMapping("/{id}")
    public Rating updateRating(@PathVariable Long id,
                             @RequestBody Rating rating) {
        return ratingService.updateRating(id, rating);
    }


    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingService.deleteById(id);
    }
}