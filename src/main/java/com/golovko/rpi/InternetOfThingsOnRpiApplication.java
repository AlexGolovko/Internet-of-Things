package com.golovko.rpi;

import com.golovko.rpi.model.RelayOne;
import com.pi4j.util.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternetOfThingsOnRpiApplication {


    public static void main(String[] args) {
        SpringApplication.run(InternetOfThingsOnRpiApplication.class, args);
    }


}
