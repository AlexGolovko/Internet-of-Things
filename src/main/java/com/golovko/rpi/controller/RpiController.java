package com.golovko.rpi.controller;

import com.golovko.rpi.model.RelayOne;
import com.golovko.rpi.model.RelayTwo;
import com.pi4j.component.relay.RelayState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/RPI")
public class RpiController {

    @Autowired
    //private RelayOne relayOne;
    private RelayTwo relayTwo;

    @GetMapping(value = "/set/{state}")
    public List<String> setDataFromRpi(@PathVariable RelayState state) {
        List<String> responce = new ArrayList<>();
        responce.add(state.name());
        responce.add("create Rela(y instance");
        relayTwo.setState(state);
        responce.add("do Smth");
        return responce;

    }

    @GetMapping(value = "/getState")
    public RelayState getStateRelay() {
        return relayTwo.getState();
    }
}
