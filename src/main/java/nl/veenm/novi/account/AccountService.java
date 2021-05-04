package nl.veenm.novi.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getAccountByEmail(String email){
        return accountRepository.findAccountByEmail(email);

    }

    public String getAccountInfo(Long accountId){
        String accountInfo = "Naam: " + accountRepository.findById(accountId).get().getFirstName() + " " +
                accountRepository.findById(accountId).get().getLastName() + "\n" +
                "Adres: " + accountRepository.findById(accountId).get().getAddress() + ", " +
                accountRepository.findById(accountId).get().getPostalCode() + " " +
                accountRepository.findById(accountId).get().getCity() + "\n" +
                "Telefoonnummer: " + accountRepository.findById(accountId).get().getPhone() + "\n" +
                "Email: " + accountRepository.findById(accountId).get().getEmail();


        return accountInfo;
    }

    @Transactional
    public void updateAccount(Long accountId, String firstName,String lastName, String email, String phone, String address, String postalCode, String city) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalStateException(
                "Account with id " + accountId + " does not exist."));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(account.getFirstName(), firstName)) {
            account.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(account.getLastName(), lastName)) {
            account.setLastName(lastName);
        }

        if (email != null && email.length() > 0 && !Objects.equals(account.getEmail(), email)) {
            Optional<Account> accountOptional = accountRepository.findAccountByEmail(email);
            if (accountOptional.isPresent()) {
                throw new IllegalStateException("Email already taken");
            } else {
                account.setEmail(email);
            }
        }
        if (phone != null && phone.length() > 0 && !Objects.equals(account.getPhone(), phone)) {
            account.setPhone(phone);
        }
        if (address != null && address.length() > 0 && !Objects.equals(account.getAddress(), address)) {
            account.setAddress(address);
        }
        if (postalCode != null && postalCode.length() > 0 && !Objects.equals(account.getPostalCode(), postalCode)) {
            account.setPostalCode(postalCode);
        }
        if (city != null && city.length() > 0 && !Objects.equals(account.getCity(), city)) {
            account.setCity(city);
        }

        accountRepository.saveAll(List.of(account));
    }

    


    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public Optional<Account> getAccount(Long accountId){
        return accountRepository.findById(accountId);
    }

    public void addNewAccount(Account account) {
        Optional<Account> accountOptional =
                accountRepository.findAccountByEmail(account.getEmail());
        if(accountOptional.isPresent()){
            throw new IllegalStateException("Email already taken");
        }

        accountRepository.save(account);

    }

    public void deleteAccount(Long accountId) {
        boolean exists = accountRepository.existsById(accountId);
        if(!exists){
            throw new IllegalStateException("Account with id " + accountId + " does not exist.");

        }
        else{
            accountRepository.deleteById(accountId);
        }

    }


    public List<Account> getCustomers() {
        return accountRepository.findAll();
    }

    public Optional<Account> getCustomer(String email) {
        return accountRepository.findAccountByEmail(email);
    }
}



