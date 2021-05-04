package nl.veenm.novi.pickup;

import nl.veenm.novi.delivery.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PickupRepository extends
        JpaRepository<Pickup, Long> {


}
