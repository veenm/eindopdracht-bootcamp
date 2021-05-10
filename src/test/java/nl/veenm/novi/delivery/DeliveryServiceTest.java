package nl.veenm.novi.delivery;

import nl.veenm.novi.placedOrder.PlacedOrder;
import nl.veenm.novi.placedOrder.PlacedOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
public class DeliveryServiceTest {



    @Autowired
    private DeliveryRepository underTestDelivery;

    @Autowired
    private PlacedOrderRepository underTestPlacedOrder;

    @MockBean
    private EntityManager entityManager;






    @Test
    void testStatusChangeToTransit(){

        Query q = mock(Query.class);
        when(q.executeUpdate()).thenReturn(420);
        when(q.setParameter(anyInt(), any())).thenReturn(q);
        when(q.setParameter(anyInt(), any())).thenReturn(q);
        when(entityManager.createNativeQuery(anyString())).thenReturn(q);

        DeliveryService deliveryService = new DeliveryService(underTestDelivery,underTestPlacedOrder, entityManager);
        Delivery delivery = new Delivery(1L, 1L, 1L,
                "Groenestraat 29", "Bemmel", "0612345678",
                true, 32F, "ready_for_delivery");
        PlacedOrder placedOrder = new PlacedOrder(1L, 1L, "10.05.2021",
                32F,"online", 4,
                true, "ready_for_delivery" );


        underTestDelivery.save(delivery);
        underTestPlacedOrder.save(placedOrder);

        deliveryService.deliveryTransit(java.util.Optional.of(placedOrder));


        assertEquals("order_in_transit", placedOrder.getStatus());


    }

    @Test
    void testStatusChangeToDone(){
        Query q = mock(Query.class);
        when(q.executeUpdate()).thenReturn(420);
        when(q.setParameter(anyInt(), any())).thenReturn(q);
        when(q.setParameter(anyInt(), any())).thenReturn(q);
        when(entityManager.createNativeQuery(anyString())).thenReturn(q);

        DeliveryService deliveryService = new DeliveryService(underTestDelivery,underTestPlacedOrder, entityManager);
        Delivery delivery = new Delivery(1L, 1L, 1L,
                "Groenestraat 29", "Bemmel", "0612345678",
                true, 32F, "order_in_transit");
        PlacedOrder placedOrder = new PlacedOrder(1L, 1L, "10.05.2021",
                32F,"online", 4,
                true, "order_in_transit" );


        underTestDelivery.save(delivery);
        underTestPlacedOrder.save(placedOrder);

        deliveryService.deliveryDone(java.util.Optional.of(placedOrder));

        assertEquals( "order_delivered",placedOrder.getStatus());


    }



}
