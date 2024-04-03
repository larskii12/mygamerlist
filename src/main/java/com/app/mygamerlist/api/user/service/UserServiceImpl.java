package com.app.mygamerlist.api.user.service;

import com.app.mygamerlist.api.game.model.Game;
import com.app.mygamerlist.api.game.service.GameService;
import com.app.mygamerlist.api.user.model.User;
import com.app.mygamerlist.api.user.model.UserLogin;
import com.app.mygamerlist.api.user.repository.UserRepository;
import com.app.mygamerlist.common.exception.NotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.app.mygamerlist.common.exception.NotFoundException.USER;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        // Hash the password before saving
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User loginUser(UserLogin login) {
        User user = userRepository.findByUsername(login.getUsername());
        if (user != null && BCrypt.checkpw(login.getPassword(), user.getPassword())) {
            // Passwords match
            return user;
        }
        // Invalid credentials
        throw new NotFoundException(USER);
    }

    @Override
    public void addPlayedGame(Long userId, Game game) {
        User user = findUserById(userId);
        user.getPlayedGames().add(game);
        saveUser(user);
    }

    @Override
    public List<Game> findAllGamesByUserId(Long userId) {
        User user = findUserById(userId);
        return user.getPlayedGames().stream().toList();
    }

    @Override
    public void removePlayedGame(Long userId, Long gameId) {
        User user = findUserById(userId);
        Game game = gameService.findGameById(gameId);
        user.getPlayedGames().remove(game);
        saveUser(user);
    }
}
