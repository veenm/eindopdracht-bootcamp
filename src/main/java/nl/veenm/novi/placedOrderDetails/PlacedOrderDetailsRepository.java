package nl.veenm.novi.placedOrderDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlacedOrderDetailsRepository extends JpaRepository<PlacedOrderDetails, Long> {


}
