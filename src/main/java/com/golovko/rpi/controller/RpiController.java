package com.golovko.rpi.controller;

import com.golovko.rpi.model.RelayOne;
import com.golovko.rpi.model.RelayTwo;
import com.pi4j.component.relay.RelayState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.List;

@RestController("/RPI")
public class RpiController {

    //private RelayOne relayOne;
    private final RelayTwo relayTwo;

    @Autowired
    public RpiController(RelayTwo relayTwo) {
        this.relayTwo = relayTwo;
    }

    @GetMapping("/")
    @ResponseBody
    public List<String>getRootPage(){
        ArrayList<String> objects = new ArrayList<>();
        objects.add(System.getProperty("os.name"));
        return objects;
    }

    @GetMapping("/set/{stateNum}")
    @ResponseBody
    public List<String> setDataFromRpi(@PathVariable int stateNum) {
        RelayState state=stateNum==0?RelayState.CLOSED:RelayState.OPEN;
        List<String> responce = new ArrayList<>();
        responce.add(state.name());
        responce.add("create Rela(y instance");
        relayTwo.setState(state);
        responce.add("do Smth");
        return responce;

    }

    @GetMapping("/getState")
    public RelayState getStateRelay() {
        return relayTwo.getState();
    }
}
