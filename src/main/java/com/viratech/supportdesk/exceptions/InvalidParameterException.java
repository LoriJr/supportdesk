package com.viratech.supportdesk.exceptions;

public class InvalidParameterException extends RuntimeException{

    public InvalidParameterException(String message){
        super(message);
    }
}
