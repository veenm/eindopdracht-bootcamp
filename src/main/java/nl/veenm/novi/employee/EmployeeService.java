package nl.veenm.novi.employee;

import nl.veenm.novi.account.Account;
import nl.veenm.novi.account.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final AccountRepository accountRepository;

    public EmployeeService(EmployeeRepository employeeRepository, AccountRepository accountRepository) {
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
    }

    public List<Account> getEmployees(){
        ArrayList<Account> employeeAccounts = new ArrayList<Account>();
        for (Account account : accountRepository.findAll()) {
            if(!account.getRole().equals("CUSTOMER")){
                employeeAccounts.add(account);
            }

        }

        return employeeAccounts;
    }

}
