package com.golovko.rpi.model;

import com.google.gson.Gson;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.util.Console;
import lombok.Data;

import java.io.IOException;

@Data
public class SensorTemperatureHumidityAM2320 implements Detectable {
    private I2CBus i2CBus;
    private static final int AM2320_ADDR = 0x5C;
    private static final byte AM2320_GET = 0x03;
    private static final byte AM2320_START_ADR = 0x00;
    private static final byte LENGTH = 0x04;
    private double humidity = Double.NaN;

    public double getHumidity() {
        try {
            measure();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return humidity;
    }

    public double getTemperature() {
        try {
            measure();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temperature;
    }

    private double temperature = Double.NaN;

    public SensorTemperatureHumidityAM2320(I2CBus bus) {
        try {
            i2CBus = I2CFactory.getInstance(bus.getBusNumber());
        } catch (I2CFactory.UnsupportedBusNumberException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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

    public void measure() throws IOException {
        I2CDevice device = i2CBus.getDevice(AM2320_ADDR);
        byte[] buf = new byte[16];
        // step 1:wake up
        device.write((byte) 0);
        // step 2:command
        // Get Humidity and Temperature
        buf[0] = 0x03;

        // Start address
        buf[1] = 0x00;

        // Length
        buf[2] = 0x04;

        int responce = device.read();
        System.out.println(responce);
        try {
            this.wait(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        responce = device.read(buf, 0, 8);
        for (int i = 0; i < buf.length-1; i++) {
            System.out.println(i+"==============="+buf[i]);
        }
/*
        ///
        // TSL2561 registers
        public static final byte TSL2561_REG_ID = (byte) 0x8A;
        public static final byte TSL2561_REG_DATA_0 = (byte) 0x8C;
        public static final byte TSL2561_REG_DATA_1 = (byte) 0x8E;
        public static final byte TSL2561_REG_CONTROL = (byte) 0x80;

        // TSL2561 power control values
        public static final byte TSL2561_POWER_UP = (byte) 0x03;
        public static final byte TSL2561_POWER_DOWN = (byte) 0x00;
        // ####################################################################
        //
        // since we are not using the default Raspberry Pi platform, we should
        // explicitly assign the platform as the BananaPro platform.
        //
        // ####################################################################
        PlatformManager.setPlatform(Platform.BANANAPRO);

        // create Pi4J console wrapper/helper
        // (This is a utility class to abstract some of the boilerplate code)
        final Console console = new Console();

        // print program title/header
        console.title("<-- The Pi4J Project -->", "I2C Example");

        // allow for user to exit program using CTRL-C
        console.promptForExit();

        // get the I2C bus to communicate on
        // - I2CBus.BUS_2 uses header pin CON6:3 as SDA and header pin CON6:5 as SCL
        // - I2CBus.BUS_3 uses header pin CON6:27 as SDA and header pin CON6:28 as SCL
        I2CBus i2c = I2CFactory.getInstance(I2CBus.BUS_2);

        // create an I2C device for an individual device on the bus that you want to communicate with
        // in this example we will use the default address for the TSL2561 chip which is 0x39.
        I2CDevice device = i2c.getDevice(TSL2561_ADDR);

        // next, lets perform am I2C READ operation to the TSL2561 chip
        // we will read the 'ID' register from the chip to get its part number and silicon revision number
        console.println("... reading ID register from TSL2561");
        int response = device.read(TSL2561_REG_ID);
        console.println("TSL2561 ID = " + String.format("0x%02x", response) + " (should be 0x50)");

        // next we want to start taking light measurements, so we need to power up the sensor
        console.println("... powering up TSL2561");
        device.write(TSL2561_REG_CONTROL, TSL2561_POWER_UP);

        // wait while the chip collects data
        Thread.sleep(500);

        // now we will perform our first I2C READ operation to retrieve raw integration
        // results from DATA_0 and DATA_1 registers
        console.println("... reading DATA registers from TSL2561");
        int data0 = device.read(TSL2561_REG_DATA_0);
        int data1 = device.read(TSL2561_REG_DATA_1);

        // print raw integration results from DATA_0 and DATA_1 registers
        console.println("TSL2561 DATA 0 = " + String.format("0x%02x", data0));
        console.println("TSL2561 DATA 1 = " + String.format("0x%02x", data1));

        // before we exit, lets not forget to power down light sensor
        console.println("... powering down TSL2561");
        device.write(TSL2561_REG_CONTROL, TSL2561_POWER_DOWN);
        ///*/
    }

    private static int crc16(byte[] ptr, int len) {
        int crc = 0xFFFF;
        int i;

        int idx = 0;
        while (len-- > 0) {
            crc ^= ptr[idx++] & 0xFF;
            for (i = 0; i < 8; i++) {
                if ((crc & 0x01) > 0) {
                    crc >>= 1;
                    crc ^= 0xA001;
                    crc &= 0xffff;

                } else {
                    crc >>= 1;
                }
            }
        }

        return crc;
    }

}
