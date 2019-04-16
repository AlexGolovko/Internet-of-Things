package com.golovko.rpi.model;

import com.pi4j.io.i2c.I2CFactory;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SensorTemperatureHumidityAM2320Test {

    @Test
    public void getData() {
    }

    @Test
    public void setData() {
    }

    @Test
    public void measure() throws IOException, I2CFactory.UnsupportedBusNumberException {
        SensorTemperatureHumidityAM2320 am2320=new SensorTemperatureHumidityAM2320(I2CFactory.getInstance(1));
        am2320.getHumidity();
        am2320.getTemperature();


    }
}