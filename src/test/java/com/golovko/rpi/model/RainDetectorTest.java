package com.golovko.rpi.model;

import com.pi4j.component.sensor.impl.GpioSensorComponent;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.impl.GpioPinImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RainDetectorTest {
    private RainDetector rainDetector;
    private GpioSensorComponent component;

    @Before
    public void setUp() throws Exception {
        GpioPinDigitalInput input = new GpioPinImpl(GpioFactory.getInstance(), GpioFactory.getDefaultProvider(), RaspiPin.GPIO_00);

        rainDetector = new RainDetector(GpioFactory.getInstance().provisionDigitalInputPin(RaspiPin.GPIO_00));
    }

    @Test
    public void isInitSucces() {

        assertNotNull(rainDetector);
    }

    @Test
    public void getNotNullData() {

        assertNotNull(rainDetector.getState());
        System.out.println(rainDetector.getState());
    }
    @Test
    public void isComponentWorking(){
        GpioSensorComponent component = new GpioSensorComponent(GpioFactory.getInstance().provisionDigitalInputPin(RaspiPin.GPIO_00));
        assertNotNull(component);
        assertNotNull(component.getState());
        System.out.println(component.getState());
    }

}