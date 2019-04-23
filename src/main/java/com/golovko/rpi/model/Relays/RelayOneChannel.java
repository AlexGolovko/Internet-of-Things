package com.golovko.rpi.model.Relays;

import com.golovko.rpi.model.Controllable;
import com.pi4j.component.relay.RelayBase;
import com.pi4j.component.relay.RelayState;
import com.pi4j.io.gpio.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class RelayOneChannel extends RelayBase implements Controllable {
    private final String isRelayOpen = "IsRelayOpen";
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

    @Override
    public boolean setData(Map<String,String>data) {
return false;
    }

    @Override
    public Map<String, String> getData() {
        RelayState state = getState();
        Map<String, String> result = new HashMap<>();
        result.put("time", new Date(System.currentTimeMillis()).toString());
        result.put(isRelayOpen, getState().name());
        return result;
    }
}
