package nl.veenm.novi.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner configCustomers(CustomerRepository repository) {
        return args -> {
            Customer mel = new Customer("Mel",
                    "van Veen", "Groenestraat 29",
                    "6681DW", "Bemmel",
                    "0645584262", "vanveenmel11@gmail.com","e5abb62efd92ea216544a6bc749b392bbba190fbdcc274a83979a871d31d370c");
            Customer pim = new Customer("Pim",
                    "van Gurp", "De Syp 519",
                    "3521AH", "Utrecht",
                    "0612365487", "pimvangurp@gmail.com","e5abb62efd92ea216544a6bc749b392bbba190fbdcc274a83979a871d31d370c");


        repository.saveAll(List.of(mel,pim));
        };


    }
}
