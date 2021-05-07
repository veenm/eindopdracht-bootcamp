package nl.veenm.novi.placedOrder;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "restaurant/api/v1/orders")
public class PlacedOrderManagementController {

    private final PlacedOrderService placedOrderService;

    public PlacedOrderManagementController(PlacedOrderService placedOrderService) {
        this.placedOrderService = placedOrderService;

    }


    @GetMapping
    public List<PlacedOrder> getOrders(){
        return placedOrderService.getOrders();
    }

    @PreAuthorize("hasRole('ROLE_CHEF')")
    @PostMapping(path = "ready/{orderId}")
    public String readyOrder(@PathVariable("orderId") Long orderId){
        return placedOrderService.readyOrder(orderId);
    }








}
