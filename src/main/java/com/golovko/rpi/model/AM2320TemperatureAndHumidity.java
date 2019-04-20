package com.golovko.rpi.model;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AM2320TemperatureAndHumidity implements Detectable {
    static Properties pythonScriptsAm2320;
    private final String RUN = "run";
    private final Logger logger = LoggerFactory.getLogger(AM2320TemperatureAndHumidity.class);

    //TODO make Singelton
    static String scripts = "pythonScripts.properties";

    static {
        pythonScriptsAm2320 = new Properties();
        try {
            pythonScriptsAm2320.load(AM2320TemperatureAndHumidity.class.getResourceAsStream(scripts));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public Map<String, Double> getTemperatureAndHumidity() {
        Process process;
        try {
            process = Runtime.getRuntime().exec(pythonScriptsAm2320.getProperty(RUN));
        } catch (IOException e) {
            e.printStackTrace();

            try {
                process = Runtime.getRuntime().exec("sudo python am2320.py");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringBuilder resultBuilder = new StringBuilder();
        try {
            InputStream inputStream = process.getInputStream();
            logger.info(String.valueOf(inputStream.available()));
            for (int i = 0; i < inputStream.available(); i++) {
                resultBuilder.append((char) inputStream.read());
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        String resultString = resultBuilder.toString();
        System.out.println(resultString);
        logger.info(resultString);
        Double temperature = Double.valueOf(resultString.substring(0, resultString.indexOf(" ")));
        Double humidity = Double.valueOf(resultString.substring(resultString.lastIndexOf(" ", resultString.length() - 1)));
        Map<String, Double> result = new HashMap<>();
        result.put("temperature", temperature);
        result.put("humidity", humidity);

        return result;
    }


    public double getHumidity() throws IOException {
        double humidity;
        try {
            Process run = Runtime.getRuntime().exec(pythonScriptsAm2320.getProperty("run"));
            run.waitFor();
            InputStream inputStream = run.getInputStream();
            if (inputStream.available() > 0) {

            }
        } catch (Exception e) {

        }
        return 0d;
    }

    ;

    public double getTemperature() {
        return 0d;
    }

    ;

    @Override
    public Gson getData() {
        return null;
    }


}
