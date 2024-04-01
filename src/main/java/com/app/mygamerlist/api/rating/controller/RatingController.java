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
//
//    @Autowired
//    private RatingService ratingService;
//
//    @GetMapping
//    public Iterable<Rating> viewRatingList() {
//        return ratingService.findAll();
//    }
//
//    @GetMapping
//    public Iterable<Rating> viewRatingListByGame() {
//        return ratingService.findAllRatingsByGameTitle();
//    }
//
//    @GetMapping
//    public Iterable<Rating> viewRatingListByUser() {
//        return ratingService.findAllRatingsByUser();
//    }
//
//    @GetMapping("/title/{gameTitle}")
//    public List<Game> findByTitle(@PathVariable String gameTitle) {
//        return gameService.findGameByTitle(gameTitle);
//    }
//
//    @GetMapping("/{id}")
//    public Game findOne(@PathVariable Long id) {
//        return gameService.findGameById(id);
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public Game createGame(@RequestBody Game game) {
//        return gameService.saveGame(game);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteGame(@PathVariable Long id) {
//        gameService.deleteById(id);
//    }
//
//    @PutMapping("/{id}")
//    public Game updateGame(@RequestBody Game game, @PathVariable Long id) {
//        return gameService.updateGame(id, game);
//    }
}