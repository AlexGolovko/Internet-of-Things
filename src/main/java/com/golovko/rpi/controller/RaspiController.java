package com.golovko.rpi.controller;

import com.golovko.rpi.model.DetectorTemperatureAndHumidity;
import com.golovko.rpi.model.RainDetector;
import com.golovko.rpi.model.Relays.RelayFactory;
import com.golovko.rpi.model.Relays.RelayOneChannel;
import com.google.gson.Gson;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class RaspiController {
    private final Logger logger = LoggerFactory.getLogger(RaspiController.class);
    private final String PWD = "admin";
    private final RainDetector rainDetector = new RainDetector(RaspiPin.GPIO_00);
    private final RelayOneChannel relayOneChannel = RelayFactory.getInstanceOneChannelRelay(RaspiPin.GPIO_01, "Relay", PinState.HIGH);
    @Autowired
    private final DetectorTemperatureAndHumidity am2320;

    public RaspiController(DetectorTemperatureAndHumidity am2320) {
        this.am2320 = am2320;
    }


    @GetMapping(value = "/")
    public ResponseEntity<String> checkPasswordAndGetAllData(@RequestHeader String password) {
        long startTime = System.currentTimeMillis();
        if (PWD.equals(password)) {
            Map<String, String> allDataFromSensors = prepareAllDataFromSensors();
            long finishTime = System.currentTimeMillis();


            logger.info(this.getClass().toString() + "===" + (finishTime - startTime) + "ms OK");


            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new Gson().toJson(allDataFromSensors));
        }
        logger.info(this.getClass().getName() + "===" + (System.currentTimeMillis() - startTime) + "ms BAD");
        return ResponseEntity
                .status(404)
                .build();
    }

    @GetMapping(value = "/json")
    public ResponseEntity<JSONObject> getAllDataToClient() {
        Map<String, String> dataFromAllSensors = rainDetector.getData();
        dataFromAllSensors.putAll(rainDetector.getData());
        dataFromAllSensors.putAll(am2320.getData());
        dataFromAllSensors.putAll(relayOneChannel.getData());
        dataFromAllSensors.remove("class");

        String s = new Gson().toJson(dataFromAllSensors);
        JSONObject result = null;

        try {
            result = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }


    private Map<String, String> prepareAllDataFromSensors() {

        Map<String, String> dataFromAllSensors = rainDetector.getData();
        dataFromAllSensors.putAll(rainDetector.getData());
        dataFromAllSensors.putAll(am2320.getData());
        dataFromAllSensors.putAll(relayOneChannel.getData());
        dataFromAllSensors.remove("class");


        return dataFromAllSensors;
    }

    @GetMapping("/setRelayState")
    public ResponseEntity<String> setRelayState(@RequestParam String state) {

        if (state != null) {
            relayOneChannel.setData((Map<String, String>) new HashMap<>().put("state", state));
            return ResponseEntity.status(HttpStatus.OK).
                    body(state + " is set");
        }
        return ResponseEntity.badRequest().build();
       /* if (state != null && state.toUpperCase().equals(RelayState.OPEN.toString())) {
            relayOneChannel.setRelayState(RelayState.OPEN);
            return ResponseEntity.ok().body(state + " is set");
        }
        if (state != null && state.toUpperCase().equals(RelayState.CLOSED.name())) {
            relayOneChannel.setRelayState(RelayState.CLOSED);
            return ResponseEntity.ok().body(state + " is set");
        }

        */
    }

    @GetMapping("/report")
    public ResponseEntity<String> getReportFromTo(@RequestParam String fromDate, @RequestParam String toDate) {

        return ResponseEntity.ok().build();
    }

}
