package com.kosenkovps.benefitcafeteria.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;


@Entity
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Role companyRole;
    private String email;

    @NonNull
    @DecimalMin(value = "0", message = "balance should not be less 0")
    private BigDecimal benefitBalance;

//    @PrePersist
//    void onCreate(){
//        this.benefitBalance = BigDecimal.ZERO;
//        this.companyRole = Role.USER;
//    }

}
