
package com.golovko.rpi.controller;

import com.golovko.rpi.model.RainDetector;
import com.golovko.rpi.model.RelayFactory;
import com.golovko.rpi.model.RelayOneChannel;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan()
public class ConfigSensors {

    @Bean
    public RainDetector rainDetector(){
        return new RainDetector(RaspiPin.GPIO_00);
    }
    @Bean
    public RelayOneChannel relayOneChannel(){
        return RelayFactory.getInstanceOneChannelRelay(RaspiPin.GPIO_01,"Relay",PinState.HIGH);
    }
}

