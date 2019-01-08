package com.fifoo.demo.exception;



public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String message){
        super(message);
    }
}
