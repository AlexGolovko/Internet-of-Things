package com.golovko.rpi.model;

import com.pi4j.component.sensor.impl.GpioSensorComponent;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.impl.GpioPinImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RainDetectorTest {
    private RainDetector rainDetector;
    private GpioSensorComponent component;

    /*@Before
    public void setUp() throws Exception {
        GpioPinDigitalInput input = new GpioPinImpl(GpioFactory.getInstance(), GpioFactory.getDefaultProvider(), RaspiPin.GPIO_00);

        rainDetector = new RainDetector(GpioFactory.getInstance().provisionDigitalInputPin(RaspiPin.GPIO_00));

    }

    @Test
    public void isInitSucces() {

        assertNotNull(rainDetector);
    }

    @Test
    public void getNotNullData() {

        assertNotNull(rainDetector.getState());
        System.out.println(rainDetector.getState());
    }
    @Test
    public void isComponentWorking(){
        GpioSensorComponent component = new GpioSensorComponent(GpioFactory.getInstance().provisionDigitalInputPin(RaspiPin.GPIO_00));
        assertNotNull(component);
        assertNotNull(component.getState());
        System.out.println(component.getState());
    }*/

    @Test
    public void testing(){

            System.out.println("<--Pi4J--> GPIO Listen Example ... started.");

            // create gpio controller
            final GpioController gpio = GpioFactory.getInstance();

            // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
            final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN);

            // set shutdown state for this input pin
            myButton.setShutdownOptions(true);

            // create and register gpio pin listener
            myButton.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                    // display pin state on console
                    System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                }

            });

            System.out.println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.");

            // keep program running until user aborts (CTRL-C)
        try {
            while(true) {

                    Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            // stop all GPIO activity/threads by shutting down the GPIO controller
            // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
            // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller

    }

}