package com.golovko.rpi.model;

import com.pi4j.component.sensor.AnalogSensor;
import com.pi4j.component.sensor.SensorBase;
import com.pi4j.component.sensor.SensorState;
import com.pi4j.component.sensor.impl.AnalogSensorComponent;
import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Component;

@Component
public class RainDetector  extends SensorBase {



    @Override
    public SensorState getState() {
        GpioController gpioController = GpioFactory.getInstance();
        GpioPinDigitalInput rainDetector = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_00, "No rain cover");
        PinState state = rainDetector.getState();
        return  SensorState.valueOf(state.getName());




    }
}
