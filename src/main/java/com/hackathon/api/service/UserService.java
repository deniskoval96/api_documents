package com.hackathon.api.service;

import com.hackathon.api.client.DiadokClient;
import com.hackathon.api.dao.UserDao;
import com.hackathon.api.domain.User;
import com.hackathon.api.domain.security.AuthToken;
import com.hackathon.api.domain.security.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.nonNull;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DiadokClient diadokClient;



    public List<User> getAllUsers() {
        System.out.println("+++++ get aLL");
        return userDao.getAllUsers();
    }


    public AuthToken login(UserCredentials userCreds) {
        String token = diadokClient.saveToken(userCreds);
        return new AuthToken(token);
    }


    public boolean isSuchTokenExist(String authToken) {
        return nonNull(diadokClient.getUserDiadokToken())
                && diadokClient.getUserDiadokToken().equals(authToken) ? Boolean.TRUE : Boolean.FALSE;
    }





//    public void createUser(User user) {
//        userDao.addUser(user);
//    }

//    public void deleteUser(int userId) {
//        userDao.deleteUser(userId);
//    }

}
