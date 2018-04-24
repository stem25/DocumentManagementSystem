package ru.it.Exception;

public class TooManyItemsException extends Exception {

    public TooManyItemsException(){
        super("Too many items");
    }
}
