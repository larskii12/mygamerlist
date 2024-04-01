package com.app.mygamerlist.api.user.service;

import com.app.mygamerlist.api.user.model.User;
import com.app.mygamerlist.api.user.model.UserLogin;
import com.app.mygamerlist.api.user.repository.UserRepository;
import com.app.mygamerlist.common.exception.NotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.app.mygamerlist.common.exception.NotFoundException.USER;

@Service
@Transactional
public class UserServiceImpl implements UserService {

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
}
