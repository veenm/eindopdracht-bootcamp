package nl.veenm.novi.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static nl.veenm.novi.security.ApplicationUserRole.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@DataJpaTest
class AccountServiceTest {

    @Autowired
    private AccountRepository underTest;





    @AfterEach
    void deleteAll(){
        underTest.deleteAll();
    }

    @Test
    void itShouldFindAccountByEmail() {
        //given
        String email = "w.vanderlinden@novi.nl";
        Account account = new Account(
                "Wendy", "van der Linden",
                "Laan van Westenenk 490","7334DS",
                "Apeldoorn", "0645215421", email,
                "$2y$10$vsTNWPBwZLRLKvhFZuTa9uxUexP898CrZzKgmmOhq5KgbLYryl2Re", CUSTOMER.name()
        );

        underTest.save(account);
        //when
        Optional<Account> exists = underTest.findAccountByEmail(email);

        //then
        assertThat(exists).isNotEmpty();

    }



    @Test
    void itShouldNotFindAccountByEmail() {
        //given
        String email = "w.vanderlinden@novi.nl";

        //when
        Optional<Account> exists = underTest.findAccountByEmail(email);

        //then
        assertThat(exists).isEmpty();

    }
}