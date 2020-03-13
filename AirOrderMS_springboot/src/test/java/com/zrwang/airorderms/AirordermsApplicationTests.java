package com.zrwang.airorderms;

import com.zrwang.airorderms.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AirordermsApplicationTests {

    @Test
    void contextLoads() {

        UserController userController = new UserController();

        userController.loginUser("zrwang","123456","","0");
    }

}
