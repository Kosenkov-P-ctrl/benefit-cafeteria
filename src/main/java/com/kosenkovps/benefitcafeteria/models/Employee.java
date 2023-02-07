package com.kosenkovps.benefitcafeteria.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
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
    //TODO add validation
    private BigDecimal benefitBalance;
}
