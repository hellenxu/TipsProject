package com.example;

/**
 * Created by xiaolin on 23/01/17.
 */

public class AnnotatedException extends Exception {
    private String exceptionMessage;

    public AnnotatedException(String msg, Object... args){
        exceptionMessage = msg;
    }

    public AnnotatedException(String msg){
        exceptionMessage = msg;
    }

    @Override
    public String toString() {
        return exceptionMessage;
    }
}
