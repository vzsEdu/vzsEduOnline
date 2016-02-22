package com.civil.report.reportService;

import lombok.extern.slf4j.Slf4j;
import org.jfree.util.Log;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leodavinci on 2015/5/17.
 */
@Slf4j
public class StateMachine {

    private String line;
    private State currentState;
    private final Map<State, AbstractAction> stateActionMap = new HashMap<State, AbstractAction>();

    public StateMachine(State initState) {
        this.currentState = initState;
    }


    public void addAction(State state, AbstractAction action) {
        stateActionMap.put(state, action);
    }

    public void processStream (BufferedReader br) {
        try {
            processLine(br);
            terminate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void processLine (BufferedReader reader) throws Exception {
        while ((line = reader.readLine()) != null) {
//            if (currentState == State.END) {
//                Log.info("End State of State Machine, will stop processing");
//                break;
//            }

            AbstractAction action = stateActionMap.get(currentState);
            State newState = action.processLine(line, currentState);
            if (newState != currentState) {
                Log.info("State is changed from " + currentState + " to " + newState);
            }
            currentState = newState;
        }

    }

    private void terminate() throws Exception {
        AbstractAction action = stateActionMap.get(currentState);
        if (action != null) {
            action.terminate(currentState);
        }
    }

}
