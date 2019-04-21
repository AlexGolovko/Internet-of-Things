package com.golovko.rpi.model;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;


public class RelayFactory {

    public static RelayOneChannel getInstanceOneChannelRelay(Pin pin, String name, PinState pinState){
        return new RelayOneChannel(pin,name,pinState);
    }
}
