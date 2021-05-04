package nl.veenm.novi.security;

import nl.veenm.novi.account.Account;
import nl.veenm.novi.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountService accountService;

    public String userEmail;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountService.getAccountByEmail(email);

        if (optionalAccount.isEmpty()) {
            throw new UsernameNotFoundException("User Name is not Found");
        }

        Account account = optionalAccount.get();

        UserDetails user = User.builder()
                .username(account.getEmail())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();

        System.out.println(account.getEmail());
        System.out.println(account.getPassword());
        System.out.println(account.getRole());
        System.out.println(account.getId());

        userEmail = account.getEmail();

        System.out.println(userEmail);

        return user;
    }
}
