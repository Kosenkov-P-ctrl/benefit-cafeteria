package com.kosenkovps.benefitcafeteria.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private BigDecimal price;
    @NonNull
    private Timestamp expirationDate;
}
