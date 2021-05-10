package nl.veenm.novi.pickup;


import nl.veenm.novi.placedOrder.PlacedOrder;
import nl.veenm.novi.placedOrder.PlacedOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PickupService {

    private final PickupRepository pickupRepository;
    private final PlacedOrderRepository placedOrderRepository;
    private EntityManager entityManager;

    @Autowired
    public PickupService(PickupRepository pickupRepository, PlacedOrderRepository placedOrderRepository) {
        this.pickupRepository = pickupRepository;

        this.placedOrderRepository = placedOrderRepository;
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


    public String pickupDone(Long orderId) {
        Optional<PlacedOrder> orderDetails = placedOrderRepository.findById(orderId);

        orderDetails.get().setStatus("order_picked_up");
        //update status
        entityManager.createNativeQuery("UPDATE placed_order SET status = ? WHERE id = ?")
                .setParameter(1, orderDetails.get().getStatus())
                .setParameter(2, orderDetails.get().getId())
                .executeUpdate();
        entityManager.createNativeQuery("UPDATE delivery SET status = ? WHERE id = ?")
                .setParameter(1, orderDetails.get().getStatus())
                .setParameter(2, orderDetails.get().getId())
                .executeUpdate();


        return "Order status has been updated";


    }
}
