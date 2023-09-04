package ru.unibell.exeptions;

public class ClientNotFoundException extends IllegalArgumentException {

    public ClientNotFoundException(String message){
        super(message);
    }
}
