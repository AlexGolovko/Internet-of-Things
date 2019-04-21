package com.golovko.rpi.model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AM2320Test {
    AM2320TemperatureAndHumidity am2320;

    @Before
    public void setUp() {
        am2320 = new AM2320TemperatureAndHumidity();
    }

    @Test
    public void shutGetTemperatureAndHumidity() {
        Map<String, Double> temperatureAndHumidity = null;
        try {
            temperatureAndHumidity = am2320.getTemperatureAndHumidity();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(temperatureAndHumidity);
        assertTrue(temperatureAndHumidity.get("temperature") > 0);
        assertTrue(temperatureAndHumidity.get("humidity") > 0);
    }

    @Test
    public void shutGetTemperature() {
        double temperature = Double.MIN_VALUE;
        try {
            temperature = am2320.getTemperature();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(temperature > -50 && temperature < 100);
    }

    @Test
    public void shutGetHumidity() {
        double humidity = Double.MIN_VALUE;
        try {
            humidity = am2320.getHumidity();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(humidity > -50 && humidity < 100);

    }
}