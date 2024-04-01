package com.app.mygamerlist.api.user.service;

import com.app.mygamerlist.api.user.model.User;
import com.app.mygamerlist.api.user.model.UserLogin;

public interface UserService {

    User saveUser(User user);

    User findUserById(Long id);

    User loginUser(UserLogin login);
}
