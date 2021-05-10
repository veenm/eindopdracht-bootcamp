package nl.veenm.novi.delivery;

import nl.veenm.novi.exceptions.DeliveryNotInTransit;
import nl.veenm.novi.placedOrder.PlacedOrder;
import nl.veenm.novi.placedOrder.PlacedOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final PlacedOrderRepository placedOrderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository, PlacedOrderRepository placedOrderRepository, EntityManager entityManager) {
        this.deliveryRepository = deliveryRepository;
        this.placedOrderRepository = placedOrderRepository;
        this.entityManager = entityManager;
    }

    public List<Delivery> getDeliveries(){
        ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
        deliveries.addAll(deliveryRepository.findAll());
        int i = 0;
        for (Delivery delivery: deliveries) {

            if(delivery.getStatus().equalsIgnoreCase("order_delivered")){
                deliveries.remove(i);
            }
            i++;

        }
        return deliveries;
    }

    @Transactional
    public String deliveryTransit(Optional<PlacedOrder> orderDetails){



        orderDetails.get().setStatus("order_in_transit");
        //update status
        entityManager.createNativeQuery("UPDATE placed_order SET status = ? WHERE id = ?")
                .setParameter(1, orderDetails.get().getStatus())
                .setParameter(2,orderDetails.get().getId())
                .executeUpdate();
        entityManager.createNativeQuery("UPDATE delivery SET status = ? WHERE id = ?")
                .setParameter(1, orderDetails.get().getStatus())
                .setParameter(2,orderDetails.get().getId())
                .executeUpdate();



        return "Order status has been updated";
    }

    @Transactional
    public String deliveryDone(Optional<PlacedOrder> orderDetails){



        if(orderDetails.get().getStatus().equalsIgnoreCase("order_in_transit")) {
            orderDetails.get().setStatus("order_delivered");
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
        else {
            throw new DeliveryNotInTransit("Delivery status is not in transit");
        }
    }

}
