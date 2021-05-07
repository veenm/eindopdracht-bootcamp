package nl.veenm.novi.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends
        JpaRepository<Delivery, Long> {

    Optional<Delivery> findDeliveryByOrderId(Long orderId);
}
