package nl.veenm.novi.delivery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public List<Delivery> getDeliveries(){
        return deliveryService.getDeliveries();
    }

    @PostMapping
    public void addDelivery(@RequestBody Delivery delivery){
        deliveryService.addNewDelivery(delivery);
    }
}
