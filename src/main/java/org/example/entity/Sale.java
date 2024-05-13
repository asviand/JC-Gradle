package org.example.entity;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

//10.2
public class Sale implements Iterable<Product>{
    private final int id;
    double amount;
    Person person;
    Map<Product, Double> products = new TreeMap<>();
    LocalDateTime timestamp;

    public Sale(int id) {
        this.id = id;
    }

    public Sale(int id, double amount, Person person, Map<Product, Double> products) {
        this.id = id;
        this.amount = amount;
        this.person = person;
        this.products = products;
    }

    public Sale(int id, double amount, Person person, Map<Product, Double> products, LocalDateTime timestamp) {
        this.id = id;
        this.amount = amount;
        this.person = person;
        this.products = products;
        this.timestamp = timestamp;
    }


    public Sale(Map<Product, Double> products, int id) {
        this.id = id;
        this.products = new TreeMap<>();
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", amount=" + amount +
                ", person=" + person +
                ", products=" + products +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public Iterator<Product> iterator() {
        return products.keySet().iterator();
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Person getPerson() {
        return person;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setProducts(Map<Product, Double> products) {
        this.products = products;
    }

    public Map<Product, Double> getProducts() {
        return products;
    }
}
