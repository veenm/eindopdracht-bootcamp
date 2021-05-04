package nl.veenm.novi.customer;

import nl.veenm.novi.account.AccountService;
import nl.veenm.novi.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerController {




    private final CustomerService customerService;
    private final AccountService accountService;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public CustomerController(AccountService accountService, CustomerService customerService, UserDetailsServiceImpl userDetailsService) {

        this.customerService = customerService;
        this.accountService = accountService;

        this.userDetailsService = userDetailsService;
    }

//    @GetMapping
//    public List<Customer> getCustomers(){
//        return customerService.getCustomers();
//    }
    @GetMapping
    public String getCustomer(){

        return customerService.getCustomer(accountService.getAccountByEmail(userDetailsService.userEmail).get().getId());
    }




    @PutMapping(path = "{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email){

        if(accountService.getAccountByEmail(userDetailsService.userEmail).get().getId() == customerId){
            customerService.updateCustomer(customerId, firstName,lastName, email);
        }
        else{
            throw new IllegalStateException("CustomerId does not match");
        }

    }

}
