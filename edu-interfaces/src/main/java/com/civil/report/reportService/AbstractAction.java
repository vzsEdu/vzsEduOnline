package com.civil.report.reportService;

import java.util.Map;

/**
 * Created by leodavinci on 2015/5/17.
 */
public abstract class AbstractAction {
    protected final AbstractAction nextAction;
    protected final Map<String, Object> output;

    protected AbstractAction(AbstractAction nextAction, Map<String, Object> output) {
        this.nextAction = nextAction;
        this.output = output;
    }

    protected void callNext(String line, State state) throws Exception {
        if (nextAction != null) {
            nextAction.processLine(line, state);
        }
    }

    public void terminate(State state) {
    }

    public abstract State processLine(String line, State state) throws Exception;
}


enum State {
    OFF("OFF - not yet running"),
    TITLE("TITLE - found report title"),
    SUBTITLE("SUBTITLE - found subtitle"),
    DETAIL("DETAIL - found detail title"),
    VALUE("VALUE - found value line"),
    PAUSE("PAUSE - pause processing with condition"),
    END("END - stop processing"),
    ERROR("ERROR - encounter error");

    private final String description;

    State(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "State: " + description;
    }
}