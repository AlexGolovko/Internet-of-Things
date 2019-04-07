package com.golovko.rpi.model;

import com.google.gson.Gson;

import java.util.UUID;

public interface Detectable {
    UUID id=UUID.randomUUID();//TODO
    Gson getData();
    void setData(Gson gson);

}
