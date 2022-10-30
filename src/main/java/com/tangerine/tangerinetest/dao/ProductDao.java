package com.tangerine.tangerinetest.dao;

import com.tangerine.tangerinetest.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface ProductDao extends CrudRepository<Product, Integer> {
}
