package com.kosenkovps.benefitcafeteria.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product products;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Timestamp purchaseDay;
    private Timestamp endDay;
    private boolean actual;
}
