package nl.veenm.novi.customer;

import nl.veenm.novi.account.Account;
import nl.veenm.novi.account.AccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "restaurant/api/v1/customers")
public class CustomerManagementController {


    private final AccountService accountService;

    public CustomerManagementController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public List<Account> getAllCustomers(){
        return accountService.getAccounts();
    }


    @GetMapping(path = "{email}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public Optional<Account> getSpecificCustomer(@PathVariable("email") String email){
        return accountService.getAccount(email);
    }




}
