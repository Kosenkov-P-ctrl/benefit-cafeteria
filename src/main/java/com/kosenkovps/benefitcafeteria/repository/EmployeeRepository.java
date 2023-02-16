package com.kosenkovps.benefitcafeteria.repository;

import com.kosenkovps.benefitcafeteria.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query(value = "select benefitBalance from Employee where id = :id")
    BigDecimal getCurrentBalanceById(Long id);

    @Modifying
    @Query(value = "update Employee e set e.benefitBalance = :benefit where e.id = :id")
    void changeBenefitBalance(Long id, BigDecimal benefit);

}
