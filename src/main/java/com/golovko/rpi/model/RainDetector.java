package com.golovko.rpi.model;

import com.pi4j.component.sensor.SensorBase;
import com.pi4j.component.sensor.SensorState;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class RainDetector extends SensorBase implements Detectable {
    private static GpioPinDigitalInput sensorInput;
    private Pin pin;


    public RainDetector(Pin pin) {
        sensorInput = GpioFactory.getInstance().provisionDigitalInputPin(pin);
        this.pin = pin;
    }


    @Override
    public SensorState getState() {
        return sensorInput.isHigh() ? SensorState.CLOSED : SensorState.OPEN;
    }


    @Override
    public Map<String, String> getData() {
        Map<String, String> result = new HashMap<>();

        result.put("isWaterOnFlow", String.valueOf(getState().equals(SensorState.OPEN)));
        return result;
    }
}

