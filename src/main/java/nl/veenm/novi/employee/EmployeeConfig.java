package nl.veenm.novi.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner configEmployees(EmployeeRepository repository){
        return args -> {
            Employee peter = new Employee("Peter",
                    "Anema", "Niels Bohrweg 121",
                    "3542 CA", "Utrecht",
                    "0307115615", "p.anema@novi.nl",
                    "Cook");
            Employee frits = new Employee("Frits",
                    "Bosschert", "Robert Schumandomein 2",
                    "6229 ES", "Maastricht",
                    "0433638333", "f.bosschert@novi.nl",
                    "Courier");
            repository.saveAll(List.of(peter, frits));
        };


        }
    }

