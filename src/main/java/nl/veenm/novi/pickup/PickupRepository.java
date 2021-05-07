package nl.veenm.novi.pickup;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PickupRepository extends
        JpaRepository<Pickup, Long> {


}
