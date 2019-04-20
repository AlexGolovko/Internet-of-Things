package com.golovko.rpi.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class RaspiController {
    private final String PWD = "admin";
    ResourceLoader resourceLoader;

    @GetMapping(value = "/")
    public ResponseEntity<String> checkPassword(@RequestHeader String password) {

        if (PWD.equals(password))
            return getAllData();
        return ResponseEntity.status(404).body("Incorrect password");
    }


    private ResponseEntity<String> getAllData() {


        Resource resource = new ClassPathResource("classpath:responceGetAllData.json");
        System.out.println("Resource =" + resource.getDescription() + "" + resource.exists());
        String responce;
        try {
            responce = new String(Files.readAllBytes(resource.getFile().toPath()));
            return ResponseEntity.ok().body(responce);
        } catch (IOException e) {
            e.printStackTrace();
        }
        responce="{\n" +
                "  \"Humidity\": 70.0,\n" +
                "  \"Tempreture\": 22.0,\n" +
                "  \"IsRelayOpen\": false,\n" +
                "  \"IsWaterOnFlow\":false\n" +
                "}\n";
        return ResponseEntity.status(201).body(responce);
    }

    @PostMapping("/setRelayState")
    public ResponseEntity<String>setRelayState(@RequestParam String state){
        //TODO
        return ResponseEntity.ok().body(state+" is set");
    }

}
