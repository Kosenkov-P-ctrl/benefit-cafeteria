package com.kosenkovps.benefitcafeteria.repositiry;

import com.kosenkovps.benefitcafeteria.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
