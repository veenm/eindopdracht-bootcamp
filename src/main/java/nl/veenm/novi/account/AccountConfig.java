package nl.veenm.novi.account;

import nl.veenm.novi.account.Account;
import nl.veenm.novi.account.AccountRepository;
import nl.veenm.novi.employee.Employee;
import nl.veenm.novi.security.ApplicationUserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static nl.veenm.novi.security.ApplicationUserRole.*;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner configAccounts(AccountRepository repository) {
        return args -> {
            Account mel = new Account("Mel",
                    "van Veen", "Groenestraat 29",
                    "6681DW", "Bemmel",
                    "0645584262", "vanveenmel11@gmail.com","$2y$10$VDDWWYB2R0nfgA9llHsdZecJWQadSKE8YMdrn.Yp0TkLSKmv7cKrq" , CUSTOMER.name()); //wachtwoord: bootcamp
            Account joost = new Account("Joost",
                    "van Aartsen", "John F. Kennedylaan 8",
                    "7314PS", "Apeldoorn",
                    "0698765432", "jvanaartsen@gmail.com","$2y$10$rA8.euKjyDNFeKyzP5NOIOKNAsssl..lOGqU/CrYkjFQ5qMl0bFEq", CUSTOMER.name()); //wachtwoord: novi2021
            Account pim = new Account("Pim",
                    "van Gurp", "De Syp 519",
                    "3521AH", "Utrecht",
                    "0612365487", "pimvangurp@gmail.com","$2y$10$mDPo3Ozo2mSGPJkXO9GQLOgynQPBhOGGALc5bVZS.Di3DMdR0vawm", COURIER.name()); //wachtwoord: pietjebel14
            Account peter = new Account("Peter",
                    "Anema", "Niels Bohrweg 121",
                    "3542 CA", "Utrecht",
                    "0307115615", "p.anema@novi.nl", "$2y$10$p29o00U6vtCKvW4FANNE8u1mYb4goMxuHZLq9CEMOXjWgReSWHgXW",CHEF.name()); //wachtwoord: springboot2021
            Account frits = new Account("Frits",
                    "Bosschert", "Robert Schumandomein 2",
                    "6229 ES", "Maastricht",
                "0433638333", "f.bosschert@novi.nl", "$2y$10$GMVzrOiICjePz5V2sIqQV.kOqSGK0oQn/GuBQYN5quCuJMyVTerB.",MANAGER.name()); //wachtwoord: java2021


        repository.saveAll(List.of(mel,pim,peter,frits,joost));
        };


    }
}
