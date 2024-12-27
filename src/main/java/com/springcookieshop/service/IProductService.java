package com.springcookieshop.service;

import com.springcookieshop.model.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);
}
