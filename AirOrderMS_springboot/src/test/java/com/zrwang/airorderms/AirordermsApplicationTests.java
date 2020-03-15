package com.zrwang.airorderms;

import com.zrwang.airorderms.controller.TicketController;
import com.zrwang.airorderms.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class AirordermsApplicationTests {

    @Test
    void contextLoads() {

        UserController userController = new UserController();

        userController.loginUser("zrwang","123456","","0");
    }

    @Test
    public void testTicket () throws ParseException {

        TicketController ticketController = new TicketController();

        ticketController.findById(1);
    }

}
