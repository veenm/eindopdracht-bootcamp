package nl.veenm.novi.placedOrderDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/restaurant/api/v1/orderdetails")
public class PlacedOrderDetailsController {

    private final PlacedOrderDetailsService placedOrderDetailsService;

    @Autowired
    public PlacedOrderDetailsController(PlacedOrderDetailsService placedOrderDetailsService) {
        this.placedOrderDetailsService = placedOrderDetailsService;
    }

    @GetMapping
    public List<PlacedOrderDetails> getOrderDetails(){
        return placedOrderDetailsService.getOrderDetails();
    }

    @GetMapping(path = ("/view/{orderId}"))
    public List<PlacedOrderDetails> getOrderDetailsByOrderId(@PathVariable ("orderId") Long orderId){
        return placedOrderDetailsService.getOrderDetailsById(orderId);
    }
}
