package nl.veenm.novi.placedOrder;

import nl.veenm.novi.exceptions.PaymentNotKnownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/orders")
public class PlacedOrderController {

    private final PlacedOrderService placedOrderService;

    @Autowired
    public PlacedOrderController(PlacedOrderService placedOrderService) {
        this.placedOrderService = placedOrderService;
    }

    @GetMapping
    public List<PlacedOrder> getOrders(){
        return placedOrderService.getOrders();
    }

    @PostMapping
    public void addOrder(@RequestBody PlacedOrder placedOrder) throws PaymentNotKnownException {
        placedOrderService.addNewOrder(placedOrder);
    }
}
