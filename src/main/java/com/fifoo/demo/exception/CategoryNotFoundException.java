package com.fifoo.demo.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message){
        super(message);
    }
}
