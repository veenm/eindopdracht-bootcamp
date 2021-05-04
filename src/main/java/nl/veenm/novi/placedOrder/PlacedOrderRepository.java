package nl.veenm.novi.placedOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public interface PlacedOrderRepository extends JpaRepository<PlacedOrder, Long> {



    Optional<PlacedOrder> findOrderByCustomerId(Long customerId);
    Optional<PlacedOrder> findPlacedOrderByPayment(String payment);
}
