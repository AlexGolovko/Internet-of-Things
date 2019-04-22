package com.golovko.rpi.controller;

import com.golovko.rpi.model.AM2320TemperatureAndHumidity;
import com.golovko.rpi.model.RainDetector;
import com.golovko.rpi.model.RelayFactory;
import com.golovko.rpi.model.RelayOneChannel;
import com.google.gson.Gson;
import com.pi4j.component.relay.RelayState;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RaspiController {
    private final Logger logger = LoggerFactory.getLogger(RaspiController.class);
    private final String PWD = "admin";
    ResourceLoader resourceLoader;

    private final RainDetector rainDetector = new RainDetector(RaspiPin.GPIO_00);
    private final RelayOneChannel relayOneChannel = RelayFactory.getInstanceOneChannelRelay(RaspiPin.GPIO_01, "Relay", PinState.HIGH);
    @Autowired
    private final AM2320TemperatureAndHumidity am2320;


   /* public RaspiController(RainDetector rainDetector, RelayOneChannel relayOneChannel, AM2320TemperatureAndHumidity am2320) {
        this.relayOneChannel = relayOneChannel;
        this.am2320 = am2320;
        this.rainDetector=rainDetector;

    }*/


    public RaspiController(AM2320TemperatureAndHumidity am2320) {
        this.am2320 = am2320;
    }


    @GetMapping(value = "/")
    public ResponseEntity<String> checkPasswordAndGetAllData(@RequestHeader String password) {
        logger.info(rainDetector.toString() + am2320.toString() + relayOneChannel.toString());
        if (PWD.equals(password))
            return getAllData();
        return ResponseEntity.status(404).body("Incorrect password");
    }


    private ResponseEntity<String> getAllData() {

        Map<String, String> dateFromAllSensors = rainDetector.getData();
        dateFromAllSensors.putAll(rainDetector.getData());
        dateFromAllSensors.putAll(am2320.getData());
        dateFromAllSensors.putAll(relayOneChannel.getData());
        dateFromAllSensors.remove("class");
        String result = new Gson().toJson(dateFromAllSensors, Map.class);

        //
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/setRelayState")
    public ResponseEntity<String> setRelayState(@RequestParam String state) {

        if (state != null && state.toUpperCase().equals(RelayState.OPEN.toString())) {
            relayOneChannel.setRelayState(RelayState.OPEN);
            return ResponseEntity.ok().body(state + " is set");
        }
        if (state != null && state.toUpperCase().equals(RelayState.CLOSED.name())) {
            relayOneChannel.setRelayState(RelayState.CLOSED);
            return ResponseEntity.ok().body(state + " is set");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(state + " is set");
    }

    @GetMapping("/report")
    public ResponseEntity<String> getReportFromTo(@RequestParam String fromDate, @RequestParam String toDate) {

        return ResponseEntity.ok().build();
    }

}
