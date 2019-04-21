package com.golovko.rpi.model;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

import static com.golovko.rpi.model.AM2320TemperatureAndHumidity.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AM2320Test {
    private final Logger logger = LoggerFactory.getLogger(AM2320Test.class);
    private AM2320TemperatureAndHumidity am2320;

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
        assertTrue(temperatureAndHumidity.get(TEMPERATURE) > 0);
        assertTrue(temperatureAndHumidity.get(HUMIDITY) > 0);
        logger.info(TEMPERATURE + "=" + temperatureAndHumidity.get(TEMPERATURE)
                + HUMIDITY + "=" + temperatureAndHumidity.get(HUMIDITY));
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