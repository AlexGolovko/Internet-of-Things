package com.golovko.rpi.model;

import com.pi4j.component.relay.RelayBase;
import com.pi4j.component.relay.RelayState;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import lombok.Data;

@Data
public class RelayOneChanel extends RelayBase {
    private RelayState relayState;
    private GpioPinDigitalOutput relay;

    RelayOneChanel(Pin pin, String name, PinState pinState) {
        relay = GpioFactory.getInstance().provisionDigitalOutputPin(pin, name, pinState);

    }

    @Override
    public RelayState getState() {
        relayState=relay.getState()==PinState.HIGH?RelayState.CLOSED:RelayState.OPEN;
        return relayState;
    }

    @Override
    public void setState(RelayState relayState) {
        this.relayState = relayState;
    }

}
