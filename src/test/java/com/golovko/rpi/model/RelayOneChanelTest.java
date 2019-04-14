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

public class RelayOneChanelTest {

    private Logger logger=Logger.getLogger(RelayOneChanelTest.class.getName());
    private RelayOneChanel relay;
    private final Pin relayPin = RaspiPin.GPIO_01;

    @Before
    public void setUp() throws Exception {
        logger.setLevel(Level.INFO);
        logger.info("RELAY==NULL= "+String.valueOf(relay==null));
        if (relay == null) {
            relay = RelayFactory.getInstanceOneChanellRelay(relayPin, "Relay", PinState.HIGH);
            logger.info(relay.toString());
        }
        assertNotNull(relay);
    }

    @Test
    public void getState() {
        RelayState relayStateBefore = relay.getState();
        PinState pinStateBefore = GpioFactory.getDefaultProvider().getState(relayPin);

        GpioFactory.getDefaultProvider().setState(relayPin, PinState.getInverseState(pinStateBefore));

        RelayState relayStateAfter = relay.getState();
        PinState pinStateAfter = GpioFactory.getDefaultProvider().getState(relayPin);

        assertNotEquals(relayStateBefore, relayStateAfter);
        assertNotEquals(pinStateBefore, pinStateAfter);
        //RaspiGpioProvider raspiGpioProvider = new RaspiGpioProvider();

    }

    @Test
    public void setState() {
        RelayState relayStateBefore = relay.getState();
        PinState pinStateBefore = GpioFactory.getDefaultProvider().getState(relayPin);

        relay.setState(RelayState.getInverseState(relayStateBefore));

        RelayState relayStateAfter = relay.getState();
        PinState pinStateAfter = GpioFactory.getDefaultProvider().getState(relayPin);
        assertNotEquals(relayStateBefore, relayStateAfter);
        assertNotEquals(pinStateBefore, pinStateAfter);
    }
}