package nl.veenm.novi.placedOrder;

import nl.veenm.novi.menuitems.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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




    @PostMapping(path = "/bestellen/{itemId}")
    public String addOrder(@PathVariable("itemId") Long itemId){
        return placedOrderService.addNewItem(itemId);
    }

    @GetMapping(path = "/bestellen")
    public List<MenuItem> getItems(){
        return placedOrderService.getOrderedItems();
    }

    @PostMapping(path = "/bestellen/place/{payment}/{delivery}")
    public ResponseEntity placeOrder(@PathVariable ("payment") String payment, @PathVariable ("delivery") boolean delivery ){
        return placedOrderService.placeOrder(payment, delivery);
    }
}
