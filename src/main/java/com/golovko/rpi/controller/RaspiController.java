package com.golovko.rpi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class RaspiController {
    private final Logger logger= LoggerFactory.getLogger(RaspiController.class);
    private final String PWD = "admin";
    ResourceLoader resourceLoader;

    /*private final RainDetector rainDetector;
    private final RelayOneChannel relayOneChannel;
    private final AM2320TemperatureAndHumidity am2320;


    public RaspiController(RainDetector rainDetector, RelayOneChannel relayOneChannel, AM2320TemperatureAndHumidity am2320) {
        this.relayOneChannel = relayOneChannel;
        this.am2320 = am2320;
        this.rainDetector=rainDetector;

    }*/

    public RaspiController() {
    }


    @GetMapping(value = "/")
    public ResponseEntity<String> checkPassword(@RequestHeader String password) {
       // logger.info(rainDetector.toString()+am2320.toString()+relayOneChannel.toString());
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

    @GetMapping("/setRelayState")
    public ResponseEntity<String>setRelayState(@RequestParam String state){
        //TODO
        return ResponseEntity.ok().body(state+" is set");
    }
    @GetMapping("/report")
    public ResponseEntity<String>getReportFromTo(@RequestParam String fromDate, @RequestParam String toDate){


        return ResponseEntity.ok().build();
    }

}
