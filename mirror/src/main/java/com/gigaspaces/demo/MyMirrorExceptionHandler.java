package com.gigaspaces.demo;

import org.openspaces.persistency.patterns.PersistencyExceptionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyMirrorExceptionHandler implements PersistencyExceptionHandler {

    protected Logger log = Logger.getLogger(this.getClass().getName());

    public MyMirrorExceptionHandler() {
    }

    @Override
    public void onException(Exception e, Object o) {
        log.log(Level.WARNING, "Error caught in mirror exception handler: " + e.getMessage(), e);
        throw new RuntimeException(e);
    }
}
