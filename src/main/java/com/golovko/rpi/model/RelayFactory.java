package com.golovko.rpi.model;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public class RelayFactory {
    public static RelayOneChanel getInstanceOneChanellRelay(Pin pin, String name, PinState pinState){
        return new RelayOneChanel(pin,name,pinState);
    }
}
