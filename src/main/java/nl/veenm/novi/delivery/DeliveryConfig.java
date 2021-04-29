package nl.veenm.novi.delivery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DeliveryConfig {

    @Bean
    CommandLineRunner configDeliveries(DeliveryRepository repository){
        return args -> {
            Delivery delivery1 = new Delivery(1L,1L,"Groenestraat 29","Bemmel","0645584262", true, 21.50f);

            repository.saveAll(List.of(delivery1));
        };
    }
}
