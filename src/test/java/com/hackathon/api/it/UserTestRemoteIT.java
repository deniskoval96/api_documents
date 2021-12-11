package com.hackathon.api.it;

import com.hackathon.api.TestConf;
import com.hackathon.api.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(TestConf.class)
//@SpringBootTest
public class UserTestRemoteIT {

    @Autowired
    UserDao userDao;

    @Test
    void test() {
        userDao.getAllUsers().forEach(System.out::println);
    }

}
