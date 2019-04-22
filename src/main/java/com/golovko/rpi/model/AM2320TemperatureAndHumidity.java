package com.golovko.rpi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
@Data
@NoArgsConstructor
@ToString
public class AM2320TemperatureAndHumidity implements Detectable {

    public static final String HUMIDITY = "humidity";
    public static final String TEMPERATURE = "temperature";
    static Properties pythonScriptsAm2320;
    private final String RUN = "run";
    private final Logger logger = LoggerFactory.getLogger(AM2320TemperatureAndHumidity.class);

    //TODO make Singelton
    static String scripts = "pythonScripts.properties";

    static {

        pythonScriptsAm2320 = new Properties();
        try {

            pythonScriptsAm2320.load(Files.newInputStream(Paths.get(scripts)));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public Map<String, Double> getTemperatureAndHumidity() throws IOException {
        Process process;
        try {

            process = Runtime.getRuntime().exec(pythonScriptsAm2320.getProperty(RUN));
        } catch (IOException e) {
            e.printStackTrace();
            process = Runtime.getRuntime().exec("sudo python am2320.py");

        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StringBuilder resultBuilder = new StringBuilder();
        InputStream inputStream = process.getInputStream();
        int i;
        while ((i = inputStream.read()) != -1) {
            resultBuilder.append((char) i);
        }
        inputStream.close();
        Double temperature = null;
        Double humidity = null;
        try {
            temperature = Double.valueOf(resultBuilder.substring(0, resultBuilder.indexOf(" ")));
            humidity = Double.valueOf(resultBuilder.substring(resultBuilder.lastIndexOf(" ")));
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        Map<String, Double> result = new HashMap<>();
        result.put(TEMPERATURE, temperature);
        result.put(HUMIDITY, humidity);

        return result;
    }


    public double getHumidity() throws IOException {


        return getTemperatureAndHumidity().get(HUMIDITY);
    }

    ;

    public double getTemperature() throws IOException {
        return getTemperatureAndHumidity().get(TEMPERATURE);
    }


    @Override
    public Map<String, String> getData() {

        Map<String, Double> temperatureAndHumidity = null;
        try {
            temperatureAndHumidity = getTemperatureAndHumidity();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(new Date(System.currentTimeMillis()).toString() + this.toString());
            return Collections.emptyMap();
        }
        Map<String, String> result = new HashMap<>();
        temperatureAndHumidity.entrySet()
                .iterator()
                .forEachRemaining(entry -> result.put(entry.getKey(), entry.getValue().toString()));
        result.put("time", new Date(System.currentTimeMillis()).toString());
        return result;
    }


}
