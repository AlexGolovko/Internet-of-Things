package com.golovko.rpi.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Properties;

public class AM2320TemperatureAndHumidity implements Detectable{
    static Properties pythonScriptsAm2320;
    //TODO make Singelton
    static String scripts ="pythonScripts.properties";
    static {
        pythonScriptsAm2320=new Properties();
        try {
            pythonScriptsAm2320.load(AM2320TemperatureAndHumidity.class.getResourceAsStream(scripts));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Process process;
    public double getHumidity() {

        return 0d;
    };
    public double getTemperature() {
        return 0d;
    };

    @Override
    public Gson getData() {
        return null;
    }

    @Override
    public void setData(Gson gson) {

    }

}
