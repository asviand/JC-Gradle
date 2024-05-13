package org.example.factory;

import org.example.entity.Product;

import java.io.IOException;

//10.8
public class ProductEntityFactory extends SimpleEntityFactory<Product> {
    @Override
    public Product create() throws IOException {
        return new Product(getNextId());
    }
}
