package com.kosenkovps.benefitcafeteria.controllers;

import com.kosenkovps.benefitcafeteria.model.Employee;
import com.kosenkovps.benefitcafeteria.model.PurchaseHistory;
import com.kosenkovps.benefitcafeteria.payload.BalanceDTO;
import com.kosenkovps.benefitcafeteria.payload.PurchaseDTO;
import com.kosenkovps.benefitcafeteria.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final Logger logger = Logger.getLogger(EmployeeController.class.getName());
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeInfo(@PathVariable Long id){
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if(employee.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee.get());
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<BigDecimal> getCurrentBalance(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getCurrentBalance(id));
    }

    @GetMapping("/benefit/{id}")
    public ResponseEntity<Iterable<PurchaseHistory>> getActualProduct(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getActualProduct(id));
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestBody PurchaseDTO purchaseDTO){
        Optional<PurchaseDTO> purchase = Optional.of(purchaseDTO);
        if(purchase.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        try{
            return employeeService.buyProduct(purchaseDTO.getEmployeeId(), purchaseDTO.getProductId()) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
        }catch (Exception e){
            logger.info("ERROR: EmployeeController.buyProduct() :" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    //TODO add validation on benefit
    @PutMapping("/add")
    public ResponseEntity<?> addToBalance(@RequestBody @Valid BalanceDTO balanceDTO){
        Optional<BalanceDTO> balance = Optional.of(balanceDTO);
        if(!balance.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        try {
            employeeService.addToBalance(balance.get().getIdSender(), balance.get().getIdTaker(), balance.get().getBenefit());
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            logger.info("ERROR: EmployeeController.addToBalance() :" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    //TODO add validation on benefit
    @PutMapping("/subtract")
    public ResponseEntity<?> subtractToBalance(@RequestBody @Valid BalanceDTO balanceDTO){
        Optional<BalanceDTO> balance = Optional.of(balanceDTO);
        if(!balance.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        try {
            employeeService.subtractToBalance(balanceDTO.getIdSender(), balanceDTO.getIdTaker(),balanceDTO.getBenefit());
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            logger.info("ERROR: EmployeeController.subtractToBalance() :" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    //API for test
    @PostMapping("/create_employee")
    public ResponseEntity<Employee> saveNewEmployee(@RequestBody Employee employee){
        Employee e = employeeService.saveNewEmployee(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(e.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
