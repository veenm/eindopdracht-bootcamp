package nl.veenm.novi.placedOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PlacedOrderRepository extends JpaRepository<PlacedOrder, Long> {


}
