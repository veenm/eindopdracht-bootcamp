package nl.veenm.novi.employee;

import nl.veenm.novi.account.Account;
import nl.veenm.novi.account.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;

import static nl.veenm.novi.security.ApplicationUserRole.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class EmployeeTest {

    private EmployeeService employeeService;

    @Autowired
    private AccountRepository underTest;


    @Test
    public void initialize() {
        EmployeeService employeeService = new EmployeeService(underTest);



        //given
        Account account1 = new Account(
                "Wendy", "van der Linden",
                "Laan van Westenenk 490", "7334DS",
                "Apeldoorn", "0645215421", "w.vanderlinden@novi.nl",
                "$2y$10$DDIdm39s2N7BPnqx7wLXfeHrF9osRlicFQhJajyT8lvxYflD.daNi", CHEF.name()
        );
        Account account2 = new Account(
                "Esmee", "Lasseur",
                "Kingsfordweg 1", "1040EE",
                "Amsterdam", "0612345678", "e.lasseur@novi.nl",
                "$2y$10$zP6nHQtH7UZscW8oIwdyoe7WZd1KiQd9gxhsXXqqyyJx2gH/EfXxm", CUSTOMER.name()
        );
        Account account3 = new Account(
                "Bill", "Gates",
                "Evert van de Beekstraat 354", "1118CZ",
                "Schiphol", "0205001500", "b.gates@hotmail.com",
                "$2y$10$vxS3acg1bKdzUIzaO2A2eOhg6vcx19Wlkd217GGbfW75/Q3kx7XPG", CUSTOMER.name()
        );

        underTest.saveAll(List.of(account1,account2,account3));

        //when
        List<Account> employees = employeeService.getEmployees();

        //then
        assertEquals(employees.get(0).getFirstName(), "Wendy");

    }





}
