package com.kosenkovps.benefitcafeteria.repository;

import com.kosenkovps.benefitcafeteria.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
