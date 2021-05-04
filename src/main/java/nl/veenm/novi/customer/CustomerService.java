package nl.veenm.novi.customer;

import nl.veenm.novi.account.Account;
import nl.veenm.novi.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final AccountRepository account;

    private Account accountDetails;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AccountRepository account) {
        this.customerRepository = customerRepository;

        this.account = account;
    }



    public String getCustomer(Long customerId){
        String accountInfo = "Naam: " + account.findById(customerId).get().getFirstName() + " " +
                account.findById(customerId).get().getLastName() + "\n" +
                "Adres: " + account.findById(customerId).get().getAddress() + ", " +
                account.findById(customerId).get().getPostalCode() + " " +
                account.findById(customerId).get().getCity() + "\n" +
                "Telefoonnummer: " + account.findById(customerId).get().getPhone() + "\n" +
                "Email: " + account.findById(customerId).get().getEmail();


        return accountInfo;
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional =
                customerRepository.findCustomerByEmail(customer.getEmail());
        if(customerOptional.isPresent()){
            throw new IllegalStateException("Email already taken");
        }

        customerRepository.save(customer);

    }

    public void deleteCustomer(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if(!exists){
            throw new IllegalStateException("Customer with id " + customerId + " does not exist.");

        }
        else{
            customerRepository.deleteById(customerId);
        }

    }

    @Transactional
    public void updateCustomer(Long customerId, String firstName,String lastName, String email, String phone, String address, String postalCode, String city) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException(
                "Customer with id " + customerId + " does not exist."));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(customer.getFirstName(), firstName)){
            customer.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(customer.getLastName(), lastName)){
            customer.setLastName(lastName);
        }

        if (email != null && email.length() > 0 && !Objects.equals(customer.getEmail(), email)){
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
            if (customerOptional.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            else{
                customer.setEmail(email);
            }
        }
        if (address != null && address.length() > 0 && !Objects.equals(customer.getAddress(), address)){
            customer.setFirstName(firstName);
        }





    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
