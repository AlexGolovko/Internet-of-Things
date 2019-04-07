package com.golovko.rpi.model;

import com.pi4j.component.relay.RelayBase;
import com.pi4j.component.relay.RelayState;
import com.pi4j.io.gpio.*;
import com.pi4j.util.Console;
import org.springframework.stereotype.Component;

@Component
public class RelayTwo extends RelayBase {
    private Console console = new Console();
    final GpioController gpioRelayLED = GpioFactory.getInstance();
    final GpioPinDigitalOutput relayLED1 = gpioRelayLED.provisionDigitalOutputPin(RaspiPin.GPIO_01, "RelayLED1", PinState.HIGH);

    @Override
    public RelayState getState() {
       return RelayState.valueOf(relayLED1.getState().toString());
    }

    @Override
    public void setState(RelayState relayState) {
        switch (relayState) {
            case OPEN:relayLED1.setState(true);break;
            case CLOSED:relayLED1.setState(false);break;
        }

    }
}
