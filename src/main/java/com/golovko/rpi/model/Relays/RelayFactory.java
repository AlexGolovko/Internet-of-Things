package com.golovko.rpi.model.Relays;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;


public class RelayFactory {

    private static RelayOneChannel relayOneChannel;

    public static RelayOneChannel getInstanceOneChannelRelay(Pin pin, String name, PinState pinState){
        if (relayOneChannel==null)
        return new RelayOneChannel(pin,name,pinState);
        else return relayOneChannel;
    }
}
