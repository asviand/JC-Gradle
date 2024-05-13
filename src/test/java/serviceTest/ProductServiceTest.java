package serviceTest;

import org.example.entity.Product;
import org.example.factory.EntityFactory;
import org.example.factory.ProductEntityFactory;
import org.example.repository.MemoryRepository;
import org.example.repository.Repository;
import org.example.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ProductServiceTest {
    private final double maxPrice = 100.1;
    private final EntityFactory<Product> factory;
    private final Repository<Product> repository;
    private final ProductService productService;

    public ProductServiceTest() throws IOException {
        factory = new ProductEntityFactory();
        repository = new MemoryRepository<Product>() {
            @Override
            public int getId(Product obj) {
                return obj.getId();
            }
        };
        productService = new ProductService(repository);

        Product product = factory.create();
        product.setName("Prod Name");
        product.setPrice(17.8);

        repository.save(product);
    }


    @Test
    public void loadAllByMaxPriceTest () throws IOException {
        List<Product> products = productService.loadAllByMaxPrice(maxPrice);
        Assertions.assertEquals(1, products.size());
    }

}
