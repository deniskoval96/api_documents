package com.hackathon.api.service;

import com.hackathon.api.dao.UserDao;
import com.hackathon.api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
//        User user = new User();
//        user.setName("Ivan");
//
//        return Arrays.asList(user);
        return userDao.getAllUsers();
    }

//    public void createUser(User user) {
//        userDao.addUser(user);
//    }

//    public void deleteUser(int userId) {
//        userDao.deleteUser(userId);
//    }

}
