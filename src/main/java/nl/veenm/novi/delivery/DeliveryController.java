package nl.veenm.novi.delivery;


import nl.veenm.novi.placedOrder.PlacedOrder;
import nl.veenm.novi.placedOrder.PlacedOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/restaurant/api/v1/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    private final PlacedOrderRepository placedOrderRepository;

    @Autowired
    public DeliveryController(DeliveryService deliveryService, PlacedOrderRepository placedOrderRepository) {
        this.deliveryService = deliveryService;
        this.placedOrderRepository = placedOrderRepository;
    }

    @GetMapping
    public List<Delivery> getDeliveries(){
        return deliveryService.getDeliveries();
    }

    @PreAuthorize("hasRole('ROLE_COURIER')")
    @PostMapping(path = "/done/{orderId}")
    public String deliveryDone(@PathVariable ("orderId") Long orderId){
        Optional<PlacedOrder> orderDetails = placedOrderRepository.findById(orderId);
        return deliveryService.deliveryDone(orderDetails);
    }

    @PreAuthorize("hasRole('ROLE_COURIER')")
    @PostMapping(path = "/deliver/{orderId}")
    public String deliveryTransit(@PathVariable ("orderId") Long orderId){
        Optional<PlacedOrder> orderDetails = placedOrderRepository.findById(orderId);
        return deliveryService.deliveryTransit(orderDetails);
    }
}
