package com.fifoo.demo.exception;

public class TitleNotFoundException extends RuntimeException {
    public TitleNotFoundException(String message){
        super(message);
    }
}
