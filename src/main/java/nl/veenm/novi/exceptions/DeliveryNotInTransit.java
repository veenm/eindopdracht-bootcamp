package nl.veenm.novi.exceptions;

public class DeliveryNotInTransit extends RuntimeException{
    public DeliveryNotInTransit(String message){
        super(message);
    }
}
