package com.momentum.active.store.controller;

import com.momentum.active.store.model.Product;
import com.momentum.active.store.service.MomentumStoreService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@Timed
@Slf4j
public class ActiveShoppeController {

    MomentumStoreService momentumStoreService;

    @Autowired
    public ActiveShoppeController(MomentumStoreService momentumStoreService) {
        this.momentumStoreService = momentumStoreService;
    }

    @GetMapping(value = "/store/products")
    @ApiOperation(value = "Returns a List of available products",
            response = Product.class,
            responseContainer = "List")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(momentumStoreService.getAllProducts());
    }

    @PostMapping(value = "/store/{customer-id}/purchase-product",consumes="application/json",
            produces="application/json")
    @ApiOperation(value = "Products purchased",
            response = Product.class,
            responseContainer = "List")
    public ResponseEntity<List<Product>> purchaseProduct(@PathVariable("customer-id") int customerId, @RequestBody List<Product> products) {
        log.info("requesting product(s): {} for customerId: {}", products, customerId);
        return ResponseEntity.ok(momentumStoreService.purchaseProduct(customerId, products));
    }


}
