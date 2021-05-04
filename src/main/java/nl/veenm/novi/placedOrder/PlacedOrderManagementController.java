package nl.veenm.novi.placedOrder;

import nl.veenm.novi.customer.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping(path = "ready/{orderId}")
    public String readyOrder(@PathVariable("orderId") Long orderId){
        return placedOrderService.readyOrder(orderId);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId){
        System.out.println(customerId);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer customerId, @RequestBody Customer customer){
        System.out.println(String.format("%s %s", customerId, customer));

    }




}
