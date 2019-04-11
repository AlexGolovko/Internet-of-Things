package com.golovko.rpi.model;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.impl.GpioPinImpl;
import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RainDetectorTest {
    private RainDetector rainDetector;

    @Before
    public void setUp() throws Exception {
        GpioPinDigitalInput input = new GpioPinImpl(GpioFactory.getInstance(), GpioFactory.getDefaultProvider(), RaspiPin.GPIO_00);
        rainDetector = new RainDetector(input);
    }
    @Test
    public void isInitSucces(){
       assertNotNull(rainDetector);
    }

    @Test
    public void getNotNullData(){
        assertNotNull(rainDetector.getState());
    }

}