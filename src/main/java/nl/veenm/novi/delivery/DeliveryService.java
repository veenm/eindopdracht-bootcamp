package nl.veenm.novi.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> getDeliveries(){
        return deliveryRepository.findAll();
    }

    public void addNewDelivery(Delivery delivery){
        Optional<Delivery> deliveryOptional =
                deliveryRepository.findDeliveryByOrderId(delivery.getOrderId());
        if(deliveryOptional.isPresent()){
            throw new IllegalStateException("Order will already be delivered");
        }

        deliveryRepository.save(delivery);

    }

}
