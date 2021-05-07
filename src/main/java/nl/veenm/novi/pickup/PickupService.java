package nl.veenm.novi.pickup;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PickupService {

    private final PickupRepository pickupRepository;

    @Autowired
    public PickupService(PickupRepository pickupRepository) {
        this.pickupRepository = pickupRepository;

    }

    public List<Pickup> getPickups(){

        ArrayList<Pickup> pickups = new ArrayList<Pickup>();
        pickups.addAll(pickupRepository.findAll());
        int i = 0;
        for (Pickup pickup: pickups) {

            if(pickup.getStatus().equalsIgnoreCase("order_picked_up") ){
                pickups.remove(i);
            }
            i++;

        }
        return pickups;
    }



}
