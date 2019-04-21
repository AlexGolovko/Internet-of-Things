package com.golovko.rpi.model;

import com.pi4j.component.relay.RelayState;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class RelayOneChannelTest {

    private Logger logger = Logger.getLogger(RelayOneChannelTest.class.getName());
    private RelayOneChannel relay;
    private final Pin relayPin = RaspiPin.GPIO_01;

    @Before
    public void setUp() {
        logger.setLevel(Level.INFO);
        logger.info("RELAY==NULL= " + (relay == null));
        relay = RelayFactory.getInstanceOneChanellRelay(relayPin, "Relay", PinState.HIGH);

        assertNotNull(relay);
    }

    @Test
    public void getState() {
        logger.info("RELAY==NULL= " + (relay == null) + Thread.currentThread().getStackTrace()[Thread.currentThread().getStackTrace().length - 1].getMethodName());

        RelayState relayStateBefore = relay.getState();
        PinState pinStateBefore = GpioFactory.getDefaultProvider().getState(relayPin);

        GpioFactory.getDefaultProvider().setState(relayPin, PinState.getInverseState(pinStateBefore));

        RelayState relayStateAfter = relay.getState();
        PinState pinStateAfter = GpioFactory.getDefaultProvider().getState(relayPin);

        assertNotEquals(relayStateBefore, relayStateAfter);
        assertNotEquals(pinStateBefore, pinStateAfter);
        relay.shutdownRelay();


    }

    @Test
    public void setState() {
        logger.info("RELAY==NULL= " + (relay == null) + Thread.currentThread().getStackTrace()[Thread.currentThread().getStackTrace().length - 1].getMethodName());

        RelayState relayStateBefore = relay.getState();
        logger.info("Relay state before ==="+relayStateBefore.name());
        PinState pinStateBefore = GpioFactory.getDefaultProvider().getState(relayPin);
        synchronized (this) {
            relay.setState(RelayState.getInverseState(relayStateBefore));
            try {
                this.wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        RelayState relayStateAfter = relay.getState();
        logger.info("Relay state after ==="+relayStateAfter.name());
        PinState pinStateAfter = GpioFactory.getDefaultProvider().getState(relayPin);
        assertNotEquals(relayStateBefore, relayStateAfter);
        assertNotEquals(pinStateBefore, pinStateAfter);
        synchronized (this){
            try {
                this.wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        relay.shutdownRelay();
    }
}