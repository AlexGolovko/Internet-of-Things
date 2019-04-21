package com.golovko.rpi;

import com.golovko.rpi.controller.ConfigSensors;
import com.golovko.rpi.controller.RaspiController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class InternetOfThingsOnRpiApplication {


    public static void main(String[] args) {
        SpringApplication.run(InternetOfThingsOnRpiApplication.class, args);
        ApplicationContext controllerContext=new AnnotationConfigApplicationContext(ConfigSensors.class);
        RaspiController raspiController=controllerContext.getBean(RaspiController.class);
    }


}
