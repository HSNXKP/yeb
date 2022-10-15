package com.wdd;


import com.wdd.server.YebApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = YebApplication.class)
public class AppTest 
{

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Test
    public void add(){
//        System.out.println(passwordEncoder.matches("1234", "$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE."));
        System.out.println(passwordEncoder.encode("123456"));


    }

}
