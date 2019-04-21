package com.golovko.rpi.model;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AM2320TemperatureAndHumidity implements Detectable {
    private final String HUMIDITY = "humidity";
    private final String TEMPERATURE = "temperature";
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

        Double temperature = Double.valueOf(resultBuilder.substring(0, resultBuilder.indexOf(" ")));
        Double humidity = Double.valueOf(resultBuilder.substring(resultBuilder.lastIndexOf(" ")));
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
    public Gson getData() {
        return null;
    }


}
