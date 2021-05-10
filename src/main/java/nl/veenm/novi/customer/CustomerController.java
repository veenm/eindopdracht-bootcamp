package nl.veenm.novi.customer;

import nl.veenm.novi.account.AccountService;
import nl.veenm.novi.placedOrder.PlacedOrder;
import nl.veenm.novi.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/v1/profiel")
public class CustomerController {





    private final AccountService accountService;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public CustomerController(AccountService accountService,  UserDetailsServiceImpl userDetailsService) {


        this.accountService = accountService;
        this.userDetailsService = userDetailsService;
    }


    @GetMapping
    public String getAccount(){

        return accountService.getAccountInfo(accountService.getAccountByEmail(userDetailsService.userEmail).get().getId());
    }

    @GetMapping(path = "/bestellingen")
    public ArrayList<PlacedOrder> getOrders(){
        return accountService.getOrders(accountService.getAccountByEmail(userDetailsService.userEmail).get().getId());
    }




    @PutMapping(path = "/aanpassen")
    public void updateAccount(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String postalCode,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String password){

            Long customerId = accountService.getAccountByEmail(userDetailsService.userEmail).get().getId();
            accountService.updateAccount(customerId, firstName,lastName, email, phone,address, postalCode, city, password);


    }

}
