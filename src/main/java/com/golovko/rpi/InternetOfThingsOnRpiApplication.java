package com.golovko.rpi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternetOfThingsOnRpiApplication {


    public static void main(String[] args) {
        SpringApplication.run(InternetOfThingsOnRpiApplication.class, args);
        //new Thread(new LocalController()).start();
    }


}
