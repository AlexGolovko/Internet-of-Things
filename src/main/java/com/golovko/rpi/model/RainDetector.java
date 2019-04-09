package com.golovko.rpi.model;

import com.pi4j.component.sensor.AnalogSensor;
import com.pi4j.component.sensor.SensorBase;
import com.pi4j.component.sensor.SensorState;
import com.pi4j.component.sensor.impl.AnalogSensorComponent;
import com.pi4j.component.sensor.impl.GpioSensorComponent;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.impl.GpioPinImpl;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public class RainDetector extends GpioSensorComponent {

   // GpioPinDigitalInput input;

    public RainDetector(GpioPinDigitalInput pin) {
        super(pin);
    }

    //private GpioSensorComponent gpioSensorComponent;



    /*private void initField() {
        GpioPinDigitalInput input = new GpioPinImpl(GpioFactory.getInstance(), GpioFactory.getDefaultProvider(), RaspiPin.GPIO_00);
        //RainDetector(input);
        //gpioSensorComponent = new GpioSensorComponent(input);
    }*/

    /*@Override
    public SensorState getState() {
        gpioSensorComponent.getState();
        gpioSensorComponent.
        *//*GpioController gpioController = GpioFactory.getInstance();
        GpioPinDigitalInput rainDetector = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_00, "No rain cover");
        PinState state = rainDetector.getState();
        return SensorState.valueOf(state.getName());*//*


    }*/
}
