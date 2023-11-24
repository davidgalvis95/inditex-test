package com.inditex.inditexttest.repository;

import com.inditex.inditexttest.model.Brand;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BrandRepository extends ReactiveCrudRepository<Brand, Integer> {
}
