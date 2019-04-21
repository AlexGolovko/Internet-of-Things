package com.golovko.rpi.model;

public interface Controllable extends Detectable {
    boolean setData(State state);
}
