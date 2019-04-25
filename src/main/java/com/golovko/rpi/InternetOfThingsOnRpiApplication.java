package com.golovko.rpi;

import com.golovko.rpi.controller.LocalController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternetOfThingsOnRpiApplication {


    public static void main(String[] args) {
        SpringApplication.run(InternetOfThingsOnRpiApplication.class, args);
        new LocalController().run();
    }


}
