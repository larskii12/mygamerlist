package com.app.mygamerlist.api.user.service;

import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.user.model.User;
import com.app.mygamerlist.api.user.model.UserLogin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User findUserById(Long id);

    User loginUser(UserLogin login);

    void addPlayedGame(Long userId, Game game);

    List<Game> findAllGamesByUserId(Long userId);

    void removePlayedGame(Long userId, Long gameId);
}
