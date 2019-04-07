package com.golovko.rpi.model;

import com.pi4j.component.relay.Relay;
import com.pi4j.component.relay.impl.GpioRelayComponent;
import com.pi4j.io.gpio.*;
import com.pi4j.util.Console;
import com.pi4j.wiringpi.GpioUtil;
import org.springframework.stereotype.Component;

@Component
public class RelayOne {
    private  Console console=new Console() ;
    public void doSmth() {
        GpioUtil.enableNonPrivilegedAccess();
        System.out.println("All LED's on Relay will turn ON..");
        final GpioController gpioRelayLED1 = GpioFactory.getInstance();
        GpioPinDigitalOutput relayLED1 = gpioRelayLED1.provisionDigitalOutputPin(RaspiPin.GPIO_00, "RelayLED1", PinState.HIGH); //OFF

        for (int i = 0; i < 20; i++) {
            console.print("ON");
            relayLED1.low(); //ON
            introduceDelay(1000);
            console.box("OFF");
            relayLED1.high();//OFF
            introduceDelay(1000);
        }

    }

    /**
     * Introduce Delay with parameter in milliseconds
     *
     * @param n Delay parameter in milliseconds
     */
    public void introduceDelay(int n) {
        try {
            console.box("Wait for " + (n / 1000) + " seconds..");
            System.out.println("Wait for " + (n / 1000) + " seconds..");
            Thread.sleep(n);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
