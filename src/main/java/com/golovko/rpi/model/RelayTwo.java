package com.golovko.rpi.model;

import com.pi4j.component.relay.RelayBase;
import com.pi4j.component.relay.RelayState;
import com.pi4j.io.gpio.*;
import com.pi4j.util.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RelayTwo extends RelayBase {
    private Console console = new Console();

    @PostConstruct
    private void init() {
        try{
        gpioRelayLED = GpioFactory.getInstance();
        relayLED1 = gpioRelayLED.provisionDigitalOutputPin(RaspiPin.GPIO_01, "RelayLED1", PinState.HIGH);}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private GpioController gpioRelayLED;
    private GpioPinDigitalOutput relayLED1;


    @Override
    public RelayState getState() {
        return RelayState.valueOf(relayLED1.getState().toString());
    }

    @Override
    public void setState(RelayState relayState) {
        switch (relayState) {
            case OPEN:
                relayLED1.setState(true);
                break;
            case CLOSED:
                relayLED1.setState(false);
                break;
        }

    }
}
