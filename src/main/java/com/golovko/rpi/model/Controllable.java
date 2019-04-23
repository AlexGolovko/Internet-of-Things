package com.golovko.rpi.model;

import java.util.Map;

public interface Controllable extends Detectable {
    boolean setData(Map<String,String> data);
}
