package com.kosenkovps.benefitcafeteria.controllers;

import com.kosenkovps.benefitcafeteria.model.PurchaseHistory;
import com.kosenkovps.benefitcafeteria.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public BigDecimal getCurrentBalance(@PathVariable Long id){
        return employeeService.getCurrentBalance(id);
    }

    @GetMapping("/benefit/{id}")
    public Iterable<PurchaseHistory> getActualProduct(@PathVariable Long id){
        return employeeService.getActualProduct(id);
    }

    @PostMapping("/buy")
    public boolean buyProduct(@RequestBody Long employeeId,@RequestBody Long productId){
        return employeeService.buyProduct(employeeId, productId);
    }

    //TODO add validation on benefit
    @PutMapping("/add")
    public void addToBalance(@RequestBody Long idSender, @RequestBody Long idTaker, @RequestBody @Valid BigDecimal benefit){
        employeeService.addToBalance(idSender, idTaker, benefit);
    }

    //TODO add validation on benefit
    @PutMapping("/subtract")
    public void subtractToBalance(@RequestBody Long idSender,@RequestBody Long idTaker,@RequestBody @Valid BigDecimal benefit){
        employeeService.subtractToBalance(idSender,idTaker, benefit);
    }


}
