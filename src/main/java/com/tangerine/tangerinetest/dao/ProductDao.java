package com.tangerine.tangerinetest.dao;

import com.tangerine.tangerinetest.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
    @Override
    @CacheEvict("products")
    <S extends Product> S save(S entity);

    @Override
    @Cacheable("product")
    Optional<Product> findById(Integer integer);

    @Override
    @Cacheable("products")
    Iterable<Product> findAll();
}
