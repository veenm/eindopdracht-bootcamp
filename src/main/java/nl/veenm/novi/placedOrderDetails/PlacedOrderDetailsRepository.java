package nl.veenm.novi.placedOrderDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlacedOrderDetailsRepository extends JpaRepository<PlacedOrderDetails, Long> {


    Optional<PlacedOrderDetails> findByPlacedOrderId(Long placedOrderId);
}
