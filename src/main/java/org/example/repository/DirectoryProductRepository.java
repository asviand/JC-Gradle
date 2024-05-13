package org.example.repository;

import org.example.entity.Person;
import org.example.entity.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class DirectoryProductRepository implements Repository<Product> {
    private final File dir;

    public DirectoryProductRepository(File dir) {
        this.dir = dir;
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("This is not a directory");
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public void save(Product obj) throws IOException {
    File file = new File(dir.getPath()+obj.getId());
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println(obj.getId());
            writer.println(obj.getName());
            writer.println(obj.getPrice());
            writer.close();
        }
    }

    @Override
    public List<Product> load(List<Integer> ids) throws IOException {
        List<Product> loadedElements = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            loadedElements.add(load(ids.get(i)));
        }
        return loadedElements;
    }

    @Override
    public Product load(int id) throws IOException {
        File file = new File(dir.getPath()+id);
        try (Scanner scanner = new Scanner(file)){
            scanner.nextLine();
            String name = scanner.nextLine();
            double price = Double.parseDouble(scanner.nextLine());
            return new Product(id, name, price);
        }
    }

    @Override
    public List<Product> loadAll() throws IOException {
        File [] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }
        List<Product> listProduct = new ArrayList<>();
        for (File file : files) {
            listProduct.add(loadFile(file));
        }
        return listProduct;
    }

    public Product loadFile (File file) {
        try (Scanner scanner = new Scanner(file)){
            int id = Integer.parseInt(scanner.nextLine());
            String name = scanner.nextLine();
            double price = Double.parseDouble(scanner.nextLine());
            return new Product(id, name, price);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
