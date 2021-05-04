package nl.veenm.novi.customer;

import nl.veenm.novi.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends
        JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findById(Optional<Account> byId);
}
