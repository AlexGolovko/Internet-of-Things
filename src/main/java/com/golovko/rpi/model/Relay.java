package com.golovko.rpi.model;

public interface Relay extends Detectable {
    boolean setData(State state);
}
