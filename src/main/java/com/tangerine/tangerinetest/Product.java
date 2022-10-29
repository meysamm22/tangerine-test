package com.tangerine.tangerinetest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "products")
public class Product {
    @Id
    private Integer prid;
    private String prdname;
}
