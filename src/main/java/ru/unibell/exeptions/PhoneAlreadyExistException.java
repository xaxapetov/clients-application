package ru.unibell.exeptions;

public class PhoneAlreadyExistException extends RuntimeException{

    public PhoneAlreadyExistException(String message){
        super(message);
    }
}
