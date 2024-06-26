package com.app.mygamerlist.api.user.controller;

import com.app.mygamerlist.api.character.model.Character;
import com.app.mygamerlist.api.character.service.CharacterService;
import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.user.model.User;
import com.app.mygamerlist.api.user.model.UserLogin;
import com.app.mygamerlist.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody UserLogin login) {
        return userService.loginUser(login);
    }

    @PostMapping("/{userId}/games")
    public Game addPlayedGame(@PathVariable Long userId,
                              @RequestBody Game game) {
        userService.addPlayedGame(userId, game);
        return game;
    }

    @GetMapping("/{userId}/games")
    public List<Game> findAllGamesByUserId(@PathVariable Long userId) {
        return userService.findAllGamesByUserId(userId);
    }

    @DeleteMapping("/{userId}/games/{gameId}")
    public void removePlayedGame(@PathVariable Long userId,
                                 @PathVariable Long gameId) {
        userService.removePlayedGame(userId, gameId);
    }
}
