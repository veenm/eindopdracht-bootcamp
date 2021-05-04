package nl.veenm.novi.employee;

import nl.veenm.novi.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "restaurant/api/v1/employees")
public class EmployeeController {



    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @GetMapping
    public List<Account> getEmployee(){
        return employeeService.getEmployees();
    }

}
