package com.momentum.active.store.service.impl;

import com.momentum.active.store.database.repository.CustomerRepository;
import com.momentum.active.store.database.repository.ProductRepository;
import com.momentum.active.store.exception.MomentumStoreBadRequest;
import com.momentum.active.store.exception.MomentumStoreResourceNotFoundException;
import com.momentum.active.store.model.Product;
import com.momentum.active.store.service.MomentumStoreService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MomentumStoreServiceImpl implements MomentumStoreService {

    ProductRepository productRepository;
    CustomerRepository customerRepository;

    @Autowired
    public MomentumStoreServiceImpl(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        val productDaoList = productRepository.findAll();
        val productList = new ArrayList<Product>();
        productDaoList.forEach(it -> productList.add(Product.builder().productSource(it).build()));
        return productList;
    }

    @Override
    public List<Product> purchaseProduct(int customerId, List<Product> products) {

        if (products.isEmpty()) {
            throw new MomentumStoreBadRequest("The customer did not provide any products to purchase");
        }

        val customer = customerRepository.findByCustomerId(customerId);

        if (null == customer) {
            throw new MomentumStoreResourceNotFoundException("User with id: " + customerId + " not found");
        }

        val customerPoints = customer.getActiveDayPoints();
        val availableProducts = getAllProducts();
        if (availableProducts.containsAll(products)) {
            val pointsRequired = products.stream().map(Product::getPoints)
                    .reduce(0, Integer::sum);
            val pointsDifference = customerPoints - pointsRequired;
            if (pointsDifference >= 0) {
                customer.setActiveDayPoints(Math.toIntExact(pointsDifference));
                customerRepository.saveAndFlush(customer);
                log.info("customer:{} purchased products: {} successfully ", customer, products);
                return products;
            }
            log.error("Number of points required: {} for purchase exceeds customers available points:{} ", pointsRequired, customerPoints);
            throw new MomentumStoreBadRequest("Number of points required: " + pointsRequired + " for purchase exceeds customers available points: " + customerPoints);

        }

        log.error("One or more of the products you selected: {} is currently unavailable. available products:{}", products, availableProducts);
        throw new MomentumStoreResourceNotFoundException("One or more of the products you selected is currently unavailable.");
    }
}
