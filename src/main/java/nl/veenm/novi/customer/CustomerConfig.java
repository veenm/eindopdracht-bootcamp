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
                    "0645584262", "vanveenmel11@gmail.com");

        Customer joost = new Customer("Joost",
                    "van Aartsen", "De Braak 23",
                    "8101GL", "Raalte",
                    "0612365478", "j.vaartsen@gmail.com");

        repository.saveAll(List.of(mel, joost));
        };


    }
}
