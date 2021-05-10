package nl.veenm.novi.exceptions;

public class PaymentNotKnownException extends RuntimeException {
    public PaymentNotKnownException(String message){
        super(message);
    }

}
