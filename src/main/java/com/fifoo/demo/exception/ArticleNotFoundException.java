package com.fifoo.demo.exception;

public class ArticleNotFoundException extends Exception{
    public ArticleNotFoundException(String message){
        super(message);
    }
}
