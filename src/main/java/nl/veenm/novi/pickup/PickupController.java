package nl.veenm.novi.pickup;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/restaurant/api/v1/pickups")
public class PickupController {

    private final PickupService pickupService;

    @Autowired
    public PickupController(PickupService pickupService) {
        this.pickupService = pickupService;

    }

    @GetMapping
    public List<Pickup> getPickups() {
        return pickupService.getPickups();
    }

    @PostMapping(path = "/done/{orderId}")
    public String pickupDone(@PathVariable ("orderId") Long orderId){
        return pickupService.pickupDone(orderId);
    }
}


