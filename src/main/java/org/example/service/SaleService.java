package org.example.service;

import org.example.entity.Sale;
import org.example.repository.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SaleService {
   private Repository<Sale> repository;

    public SaleService(Repository<Sale> repository) {
        this.repository = repository;
    }

    public List<Sale> loadAllByPersonId (int id) throws IOException {
        return repository
                .loadAll()
                .stream()
                .filter(x -> x != null && x.getPerson() != null && x.getPerson().getId() == id)
                .collect(Collectors.toList());
    }

    public Repository<Sale> getRepository() {
        return repository;
    }

    public void setRepository(Repository<Sale> repository) {
        this.repository = repository;
    }
}
