package com.golovko.rpi.model;

import com.pi4j.component.relay.RelayBase;
import com.pi4j.component.relay.RelayState;
import com.pi4j.io.gpio.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RelayOneChannel extends RelayBase {
    private final Pin relayPin;
    private RelayState relayState;
    private GpioPinDigitalOutput relay;
    private final String name;
    private final GpioController gpioController;


    RelayOneChannel(Pin pin, String name, PinState pinState) {
        gpioController = GpioFactory.getInstance();
        relay = gpioController.provisionDigitalOutputPin(pin, name, pinState);
        relayPin = pin;
        this.name = name;
    }

    @Override
    public RelayState getState() {
        relayState = relay.getState() == PinState.HIGH ? RelayState.CLOSED : RelayState.OPEN;
        return relayState;
    }

    @Override
    public void setState(RelayState relayState) {
        relay.setState(relayState == RelayState.OPEN ? PinState.LOW : PinState.HIGH);
        this.relayState = relayState;
    }

    public void shutdownRelay() {
        gpioController.shutdown();
        gpioController.unprovisionPin(relay);
    }
}
