package com.golovko.rpi.model;

import com.pi4j.component.sensor.SensorState;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RainDetectorTest {
    private RainDetector rainDetector;
    private final Pin rainPin = RaspiPin.GPIO_00;
    //private GpioSensorComponent component;

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

    @Before
    public void setUp() throws Exception {
        if (rainDetector == null) {
            rainDetector = new RainDetector(rainPin);
        }
        assertNotNull(rainDetector);
    }

    @Test
    public void RainGetStateTest() {
        SensorState rainDetectorState = rainDetector.getState();
        PinState pinStateBefore = GpioFactory.getDefaultProvider().getState(rainPin);

        assertNotNull(rainDetectorState);

        System.out.println(rainDetector.getState());
        System.out.println(rainDetector.isClosed());
        System.out.println(rainDetector.isOpen());
        rainDetector.addListener(event -> {
            System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getNewState());
        });


    }
    /*@Test
    public void testing(){

            System.out.println("<--Pi4J--> GPIO Listen Example ... started.");

            // create gpio controller
            final GpioController gpio = GpioFactory.getInstance();

            // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
            final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN);

            // set shutdown state for this input pin
            myButton.setShutdownOptions(true);

            // create and register gpio pin listener
            myButton.addListener((GpioPinListenerDigital) event -> {
                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            });

            System.out.println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.");*/

    // keep program running until user aborts (CTRL-C)
        /*try {
            while(true) {

                    Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
          }*/

    // stop all GPIO activity/threads by shutting down the GPIO controller
    // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
    // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller


}