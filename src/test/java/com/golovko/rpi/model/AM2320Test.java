package com.golovko.rpi.model;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class AM2320Test {

    @Test
    public void shutGetTemperatureAndHumidity() {
        AM2320TemperatureAndHumidity am2320=new AM2320TemperatureAndHumidity();
        Map<String, Double> temperatureAndHumidity = am2320.getTemperatureAndHumidity();
        assertNotNull(temperatureAndHumidity);
        assertTrue(temperatureAndHumidity.get("temperature")>0);
        assertTrue(temperatureAndHumidity.get("humidity")>0);
    }
}