package com.momentum.active.store.service;

import com.momentum.active.store.model.Product;

import java.util.List;

public interface MomentumStoreService {

   List<Product> getAllProducts();

   List<Product> purchaseProduct(int customerId, List<Product> products);
}
