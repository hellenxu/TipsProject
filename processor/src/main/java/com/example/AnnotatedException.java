package com.example;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-01-23.
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
