package com.golovko.rpi.controller;

import com.golovko.rpi.model.RelayOne;
import com.golovko.rpi.model.RelayTwo;
import com.pi4j.component.relay.RelayState;
import com.pi4j.component.relay.impl.GpioRelayComponent;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.impl.GpioPinImpl;
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

    @GetMapping("RPI/set/{stateNum}")
    @ResponseBody
    public List<String> setDataFromRpi(@PathVariable int stateNum) {
        List<String> responce = new ArrayList<>();

        //
        GpioPinImpl gpioPin = new GpioPinImpl(GpioFactory.getInstance(), GpioFactory.getDefaultProvider(), RaspiPin.GPIO_01);
        GpioRelayComponent gpioRelay=new GpioRelayComponent(gpioPin, PinState.HIGH,PinState.LOW);
        responce.add(gpioPin.getState().getName()+"==="+gpioPin.getState().getValue());
        responce.add(gpioRelay.getState().name());
        gpioRelay.setState(RelayState.getInverseState(gpioRelay.getState()));
        responce.add(gpioRelay.getState().name());
        //

        /*RelayState state=stateNum==0?RelayState.CLOSED:RelayState.OPEN;
        responce.add(state.name());
        responce.add("create Rela(y instance");
        relayTwo.setState(state);
        responce.add("do Smth");*/
        return responce;

    }

    @GetMapping("/getState")
    public RelayState getStateRelay() {
        return relayTwo.getState();
    }
}
