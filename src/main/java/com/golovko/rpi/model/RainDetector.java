package com.golovko.rpi.model;

import com.pi4j.component.sensor.SensorBase;
import com.pi4j.component.sensor.SensorState;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import lombok.Data;

@Data
/*@Component
@Scope("prototype")*/
public class RainDetector extends SensorBase {
    ///private Sensor sensor;
    private static GpioPinDigitalInput sensorInput;
    //private RainDetector instance;


    //    @PostConstruct
//    public static RainDetector getInstance(Pin pin) {
//
//       sensorInput = GpioFactory.getInstance().provisionDigitalInputPin(pin);
//       return new RainDetector();
//    }
    public RainDetector(Pin pin) {
        sensorInput = GpioFactory.getInstance().provisionDigitalInputPin(pin);

    }

    @Override
    public SensorState getState() {
        return sensorInput.isHigh() ? SensorState.CLOSED : SensorState.OPEN;
    }


    // GpioPinDigitalInput input;

    /*public RainDetector(GpioPinDigitalInput pin) {
        super(pin);
    }*/


    //private GpioSensorComponent gpioSensorComponent;



    /*private void initField() {
        GpioPinDigitalInput input = new GpioPinImpl(GpioFactory.getInstance(), GpioFactory.getDefaultProvider(), RaspiPin.GPIO_00);
        //RainDetector(input);
        //gpioSensorComponent = new GpioSensorComponent(input);
    }*/

    /*@Override
    public SensorState getState() {
        gpioSensorComponent.getState();
        gpioSensorComponent.
        *//*GpioController gpioController = GpioFactory.getInstance();
        GpioPinDigitalInput rainDetector = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_00, "No rain cover");
        PinState state = rainDetector.getState();
        return SensorState.valueOf(state.getName());*//*


    }*/


}
