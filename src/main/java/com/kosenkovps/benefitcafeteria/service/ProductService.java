package com.kosenkovps.benefitcafeteria.service;

import com.kosenkovps.benefitcafeteria.models.Product;
import com.kosenkovps.benefitcafeteria.repositiry.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void addProduct(String name, BigDecimal price){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Iterable<Product> getAvailableProducts(){
        return productRepository.findAll();
    }
}
