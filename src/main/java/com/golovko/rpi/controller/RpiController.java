package com.golovko.rpi.controller;

import com.golovko.rpi.model.RelayOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/RPI")
public class RpiController{

    @Autowired
    private RelayOne relayOne;

    @GetMapping
    public List<String> getDataFromRpi(){
        List<String>responce=new ArrayList<>();
        responce.add("create Relay instance");
        relayOne.doSmth();
        responce.add("do Smth");
        return responce;

    }

}
