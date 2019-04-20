package com.golovko.rpi.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class RaspiController {
    private final String PWD = "admin";

    @GetMapping(value = "/")
    public ResponseEntity<String> checkPassword(@RequestHeader String password) {

        if (PWD.equals(password))
            return getAllData();
        return ResponseEntity.status(404).body("Incorrect password");
    }


    private ResponseEntity<String> getAllData() {

        Resource resource = new ClassPathResource("resources/responceGetAllData.json");
        System.out.println("Resource =" + resource.getDescription() + "" + resource.exists());
        String responce;
        try {
            responce = new String(Files.readAllBytes(resource.getFile().toPath()));
            return ResponseEntity.ok().body(responce);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(404).body("Fail reading data");
    }

}
