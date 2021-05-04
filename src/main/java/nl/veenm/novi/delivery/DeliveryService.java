package nl.veenm.novi.delivery;

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
    public DeliveryService(DeliveryRepository deliveryRepository, PlacedOrderRepository placedOrderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.placedOrderRepository = placedOrderRepository;
    }

    public List<Delivery> getDeliveries(){
        ArrayList<Delivery> deliverys = new ArrayList<Delivery>();
        deliverys.addAll(deliveryRepository.findAll());
        int i = 0;
        for (Delivery delivery: deliverys) {

            if(delivery.getStatus().equalsIgnoreCase("order_delivered")){
                deliverys.remove(i);
            }
            i++;

        }
        return deliverys;
    }

    @Transactional
    public String deliveryTransit(Long orderId){
        Optional<PlacedOrder> orderDetails = placedOrderRepository.findById(orderId);


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
    public String deliveryDone(Long orderId){
        Optional<PlacedOrder> orderDetails = placedOrderRepository.findById(orderId);


        orderDetails.get().setStatus("order_delivered");
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

}
