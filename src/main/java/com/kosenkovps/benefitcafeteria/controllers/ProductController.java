package com.kosenkovps.benefitcafeteria.controllers;

import com.kosenkovps.benefitcafeteria.model.Product;
import com.kosenkovps.benefitcafeteria.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final Logger logger = Logger.getLogger(ProductController.class.getName());
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAvailableProducts(){
        return ResponseEntity.ok(productService.getAvailableProducts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product productDTO){
        Optional<Product> product = Optional.of(productDTO);
        if(product.isEmpty()){
            ResponseEntity.badRequest().build();
        }
        try {
            productService.addProduct(product.get().getName(), product.get().getPrice(), product.get().getExpirationDate().getTime());
            return ResponseEntity.ok().build();
        }catch (Exception e){
            logger.info("ERROR: ProductController.addProduct() :" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody Long id){
        if(id == null){
            ResponseEntity.badRequest().build();
        }
        try{
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            logger.info("ERROR: ProductController.deleteProduct() :" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
