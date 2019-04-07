package com.golovko.rpi.model;

import com.google.gson.Gson;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.util.Console;
import org.springframework.stereotype.Component;

@Component
public class SensorTemperatureHumidityAM2320 implements Detectable {

    private static int AM2320_ADDR = 0x5C;
    private double humidity = Double.NaN;
    private double temperature = Double.NaN;


    @Override
    public Gson getData() {

        final Console console = new Console();
        console.title("<-- The Pi4J Project -->", "I2C Example");

        /*try (I2CBus i2CBus = I2CFactory.getInstance(I2CBus.BUS_1)) {
            System.out.println("result");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO
        //I2CDevice deviceAm2320=I2C.
        ;*/
        return null;
    }

    @Override
    public void setData(Gson gson) {

    }

}
