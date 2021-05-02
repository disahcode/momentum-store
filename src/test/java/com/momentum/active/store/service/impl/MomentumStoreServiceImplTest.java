package com.momentum.active.store.service.impl;

import com.google.common.collect.ImmutableList;
import com.momentum.active.store.database.repository.CustomerRepository;
import com.momentum.active.store.exception.MomentumStoreBadRequest;
import com.momentum.active.store.exception.MomentumStoreResourceNotFoundException;
import com.momentum.active.store.service.MomentumStoreService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static com.momentum.active.store.MomentumStoreUtil.getFakeProduct;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MomentumStoreServiceImplTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MomentumStoreService classUnderTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void should_get_all_available_products() {
        assertNotNull(classUnderTest);
        assertEquals(5, classUnderTest.getAllProducts().size());
    }

    @Test
    void should_fail_with_ActiveShoppeBadRequestException_when_product_list_is_empty() {
        assertThrows(MomentumStoreBadRequest.class, () ->
                        classUnderTest.purchaseProduct(1, Collections.emptyList())
                , "Products for purchase not provided");
    }

    @Test
    void should_fail_with_ActiveShoppeResourceNotFoundException_when_customer_is_not_found() {
        val customerId = -10000;
        assertThrows(MomentumStoreResourceNotFoundException.class, () -> {
            classUnderTest.purchaseProduct(customerId, ImmutableList.of(getFakeProduct(0, "Fake Name", 0)));
        }, "User with id: " + customerId + " not found");
    }

    @Test
    void should_fail_with_ActiveShoppeResourceNotFoundException_when_one_or_more_products_is_not_found() {
        val customerId = 1;
        assertThrows(MomentumStoreResourceNotFoundException.class, () -> {
            classUnderTest.purchaseProduct(customerId, ImmutableList.of(getFakeProduct(0, "Fake Name", 0)));
        }, "Product you selected is not available.");
    }


    @Test
    void should_purchase_available_product_and_decrement_customer_available_points_products() {
        val productsPurchased = classUnderTest.purchaseProduct(1, ImmutableList.of(getFakeProduct(5, "Kanstock", 50)));
        assertEquals(1, productsPurchased.size());
        assertEquals(50, customerRepository.findByCustomerId(1).getActiveDayPoints());
    }


}