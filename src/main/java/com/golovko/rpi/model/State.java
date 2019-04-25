package com.golovko.rpi.model;

import com.pi4j.component.relay.RelayState;

public enum  State {
    OPEN,CLOSE;
    public RelayState getRelayState(State state){
        return state.ordinal()==0?RelayState.OPEN:RelayState.CLOSED;
    }
}
