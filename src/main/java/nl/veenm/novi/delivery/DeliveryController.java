package nl.veenm.novi.delivery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/restaurant/api/v1/deliveries")
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

    @PreAuthorize("hasRole('ROLE_COURIER')")
    @PostMapping(path = "/done/{orderId}")
    public String deliveryDone(@PathVariable ("orderId") Long orderId){
        return deliveryService.deliveryDone(orderId);
    }
    @PostMapping(path = "/deliver/{orderId}")
    public String deliveryTransit(@PathVariable ("orderId") Long orderId){
        return deliveryService.deliveryTransit(orderId);
    }
}
