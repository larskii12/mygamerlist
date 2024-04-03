package com.app.mygamerlist.api.rating.controller;

import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.game.service.GameService;
import com.app.mygamerlist.api.rating.model.Rating;
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

    @GetMapping
    public Iterable<Rating> viewRatingList() {
        return ratingService.findAll();
    }

    @GetMapping("/games")
    public Iterable<Rating> viewRatingListByGame(@RequestParam(required = false) Long gameId,
                                                 @RequestParam(required = false) String title) {
        return ratingService.findAllRatingsByGame(gameId, title);
    }

    @GetMapping("/characters")
    public Iterable<Rating> viewRatingListByCharacter(@RequestParam(required = false) Long characterId,
                                                      @RequestParam(required = false) String name) {
        return ratingService.findAllRatingsByCharacter(characterId, name);
    }

    @GetMapping("/users/{userId}")
    public Iterable<Rating> viewRatingListByUser(@PathVariable Long userId,
                                                 @RequestParam(required = false) String username) {
        return ratingService.findAllRatingsByUser(userId, username);
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