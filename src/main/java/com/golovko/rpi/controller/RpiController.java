package com.golovko.rpi.controller;

import com.golovko.rpi.model.RainDetector;
import com.golovko.rpi.model.RelayOne;
import com.golovko.rpi.model.RelayTwo;
import com.pi4j.component.relay.RelayState;
import com.pi4j.component.relay.impl.GpioRelayComponent;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.impl.GpioPinImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

@RestController
public class RpiController {

    //private RelayOne relayOne;
    private final RelayTwo relayTwo;
    private RainDetector rainDetector;
    private final Logger logger=Logger.getLogger(this.getClass());

    @Autowired
    public RpiController(RelayTwo relayTwo) {
        this.relayTwo = relayTwo;
        GpioPinDigitalInput input = new GpioPinImpl(GpioFactory.getInstance(), GpioFactory.getDefaultProvider(), RaspiPin.GPIO_00);
        rainDetector = new RainDetector(input);
    }

    @GetMapping(value = "/")
    @ResponseBody
    public List<String> getRootPage() {
        ArrayList<String> objects = new ArrayList<>();
        objects.add(System.getProperty("os.name"));
        return objects;
    }

    @GetMapping(value = "RPI/set/{stateNum}")
    @ResponseBody
    public List<String> setDataFromRpi(@PathVariable int stateNum) {
        List<String> responce = new ArrayList<>();


        //
        GpioPinImpl gpioPin = new GpioPinImpl(GpioFactory.getInstance(), GpioFactory.getDefaultProvider(), RaspiPin.GPIO_01);
        GpioRelayComponent gpioRelay = new GpioRelayComponent(gpioPin, PinState.HIGH, PinState.LOW);
        responce.add(gpioPin.getState().getName() + "===" + gpioPin.getState().getValue());
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

    @GetMapping(value = "RPI/getState")
    public RelayState getStateRelay() {
        logger.trace(relayTwo.getState());
        return relayTwo.getState();
    }

    @GetMapping(value = "RPI/getRainState")
    public String getRainState() {
        System.out.println("Request"+System.currentTimeMillis());
        logger.setLevel(Level.ALL);
        logger.error(rainDetector);
        System.out.println(rainDetector);
        System.out.println(rainDetector.getName());
        System.out.println(rainDetector.isClosed());
        System.out.println(rainDetector.isClosed());
        System.out.println(rainDetector.getProperties());
        return rainDetector.getState().toString();
    }
}
