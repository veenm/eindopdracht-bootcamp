package nl.veenm.novi.exceptions;

public class OrderAlreadyDeliveredException extends Exception{
    public OrderAlreadyDeliveredException(String message){
        super(message);
    }
}
