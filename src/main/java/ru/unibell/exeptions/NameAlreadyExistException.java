package ru.unibell.exeptions;

public class NameAlreadyExistException extends RuntimeException {

    public NameAlreadyExistException(String message){
        super(message);
    }
}
