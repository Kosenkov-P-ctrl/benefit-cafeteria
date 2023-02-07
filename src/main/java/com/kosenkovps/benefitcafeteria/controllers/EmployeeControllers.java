package com.kosenkovps.benefitcafeteria.controllers;

import com.kosenkovps.benefitcafeteria.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeControllers {
    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public BigDecimal getCurrentBalance(@PathVariable Long id){
        return employeeService.getCurrentBalance(id);
    }

    @GetMapping("/")

    //TODO add validation on benefit
    @PutMapping("/add")
    public void addToBalance(@RequestBody Long idSender, @RequestBody Long idTaker, @RequestBody BigDecimal benefit){
        employeeService.addToBalance(idSender, idTaker, benefit);
    }

    //TODO add validation on benefit
    @PutMapping("/subtract")
    public void subtractToBalance(@RequestBody Long idSender,@RequestBody Long idTaker,@RequestBody BigDecimal benefit){
        employeeService.subtractToBalance(idSender,idTaker, benefit);
    }


}
