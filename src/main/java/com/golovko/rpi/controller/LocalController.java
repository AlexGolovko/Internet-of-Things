package com.golovko.rpi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class LocalController implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(LocalController.class);

    @Override
    public void run() {
        logger.info("Run method");
        Date date= new Date();
        while (true) {
                    if(System.currentTimeMillis()>1);
        }


    }
}
