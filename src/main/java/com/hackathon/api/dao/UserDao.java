package com.hackathon.api.dao;

import com.hackathon.api.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {


//    User getUserByUsername(@Param("username") String username);

    /// ---------------

    List<User> getAllUsers();

//    void addUser(User user);

//    void deleteUser(int userId);



}
