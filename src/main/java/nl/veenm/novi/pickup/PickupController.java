package nl.veenm.novi.pickup;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/restaurant/api/v1/pickup")
public class PickupController {

    private final PickupService pickupService;

    @Autowired
    public PickupController(PickupService pickupService) {
        this.pickupService = pickupService;

    }

    @GetMapping
    public List<Pickup> getDeliveries() {
        return pickupService.getPickups();
    }
}


