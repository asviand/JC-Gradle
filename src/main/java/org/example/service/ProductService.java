package org.example.service;

import org.example.entity.Product;
import org.example.repository.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private Repository<Product> repository;

    public ProductService(Repository<Product> repository) {
        this.repository = repository;
    }
    public List<Product> loadAllByMaxPrice (double maxPrice) throws IOException {
        return repository
                .loadAll()
                .stream()
                .filter(x -> x != null && x.getPrice() < maxPrice)
                .collect(Collectors.toList());
    }

    public Repository<Product> getRepository() {
        return repository;
    }

    public void setRepository(Repository<Product> repository) {
        this.repository = repository;
    }
}
