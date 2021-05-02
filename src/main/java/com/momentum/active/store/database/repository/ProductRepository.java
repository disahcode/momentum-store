package com.momentum.active.store.database.repository;

import com.momentum.active.store.database.ProductDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductDao, Integer> {
    List<ProductDao> findAll();
}
