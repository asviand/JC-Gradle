package org.example.repository;

import org.example.entity.Person;
import org.example.entity.Product;
import org.example.entity.Sale;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;


public class DirectorySaleRepository implements Repository<Sale> {
    private final File dir;

    public DirectorySaleRepository(File dir) {
        this.dir = dir;
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("This is not a directory");
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    @Override
    public void save(Sale obj) throws IOException {
        File file = new File(dir.getPath()+obj.getId());
        try (PrintWriter writer = new PrintWriter(file)){
            writer.println(obj.getId());
            writer.println(obj.getAmount());
            writer.println(obj.getPerson().getId());
            writer.println(obj.getPerson().getAge());
            writer.println(obj.getPerson().getName());
            writer.println(obj.getProducts().size());
                for (Map.Entry<Product, Double> entry : obj.getProducts().entrySet()) {
                writer.println(entry.getKey().getId());
                writer.println(entry.getKey().getName());
                writer.println(entry.getKey().getPrice());
                }
            writer.close();
        }

    }
    @Override
    public Sale load(int id) throws IOException {
        File file = new File(dir.getPath()+id);
        return load(file);
    }

    @Override
    public List<Sale> loadAll() throws IOException {
        File [] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }
        List<Sale> listSale = new ArrayList<>();
        for (File file : files) {
            listSale.add(load(file));
        }
        return listSale;
    }

    @Override
    public List<Sale> load(List<Integer> ids) throws IOException {
        List<Sale> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            list.add(load(ids.get(i)));
        }
        return list;
    }

    private Sale load(File file)  {
        try (Scanner scanner = new Scanner(file)){
            int id = Integer.parseInt(scanner.nextLine());
            double amount = Double.parseDouble(scanner.nextLine());
            int personId = Integer.parseInt(scanner.nextLine());
            int personAge = Integer.parseInt(scanner.nextLine());
            String personName = scanner.nextLine();
            Person newPerson = new Person(personId, personAge, personName);
            int productsSize = Integer.parseInt(scanner.nextLine());
            Map<Product, Double> products = new HashMap<>();
            for (int i = 0; i < productsSize; i++) {
                int productId = Integer.parseInt(scanner.nextLine());
                String productName = scanner.nextLine();
                double price = Double.parseDouble(scanner.nextLine());
                Product product = new Product(productId, productName, price);
                products.put(product,Double.parseDouble(scanner.nextLine()));
            }
            return new Sale(id, amount, newPerson, products);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
