package nl.veenm.novi.placedOrder;

import nl.veenm.novi.exceptions.PaymentNotKnownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlacedOrderService {

    private final PlacedOrderRepository placedOrderRepository;

    @Autowired
    public PlacedOrderService(PlacedOrderRepository placedOrderRepository) {
        this.placedOrderRepository = placedOrderRepository;
    }

    public List<PlacedOrder> getOrders(){
        return placedOrderRepository.findAll();
    }

    public void addNewOrder(PlacedOrder placedOrder) throws PaymentNotKnownException {
        Optional<PlacedOrder> placedOrderOptional = placedOrderRepository.findPlacedOrderByPayment(placedOrder.getPayment().toLowerCase());
        if(placedOrderOptional.equals("online") || placedOrderOptional.equals("cash") ){
            throw new PaymentNotKnownException("Payment is not known");

        }
        placedOrderRepository.save(placedOrder);
    }

}
