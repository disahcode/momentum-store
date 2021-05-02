package com.momentum.active.store;

import com.momentum.active.store.model.Product;

public class MomentumStoreUtil {

    public static Product getFakeProduct(int productCode, String productName, int pointsCost) {
        return Product.builder()
                .code(productCode)
                .name(productName)
                .points(pointsCost)
                .build();
    }
}
