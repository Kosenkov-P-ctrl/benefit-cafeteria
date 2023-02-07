package com.kosenkovps.benefitcafeteria.controllers;

import com.kosenkovps.benefitcafeteria.models.Product;
import com.kosenkovps.benefitcafeteria.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Iterable<Product> getAvailableProducts(){
        return productService.getAvailableProducts();
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody String name,@RequestBody BigDecimal price,@RequestBody Long expirationDate){
        productService.addProduct(name, price, expirationDate);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Long id){
        productService.deleteProduct(id);
    }
}
