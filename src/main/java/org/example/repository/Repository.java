package org.example.repository;

import java.io.IOException;
import java.util.List;
//9.1
public interface Repository<T> {


    void save(T person) throws IOException;

//9.2
    List<T> load(List<Integer> ids) throws IOException;

    T load (int id) throws IOException;

    List<T> loadAll () throws IOException;
}




