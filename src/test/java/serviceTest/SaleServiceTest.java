package serviceTest;

import org.example.entity.Person;
import org.example.entity.Sale;
import org.example.factory.EntityFactory;
import org.example.factory.SaleEntityFactory;
import org.example.repository.MemoryRepository;
import org.example.repository.Repository;
import org.example.service.SaleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class SaleServiceTest {
private int personId = 1;
    private  EntityFactory<Sale> factory;
    private  Repository<Sale> repository;
    private  SaleService saleService;

    public SaleServiceTest() throws IOException {
        factory = new SaleEntityFactory();
        repository = new MemoryRepository<Sale>() {
            @Override
            public int getId(Sale obj) {
                return obj.getId();
            }
        };
        saleService = new SaleService(repository);

        Sale sale = factory.create();

        sale.setPerson(new Person(personId));

        repository.save(sale);
    }

    @Test
    public void loadAllByPersonIdTest () throws IOException {
        List<Sale> sales = saleService.loadAllByPersonId(personId);
        Assertions.assertEquals(1, sales.size());
    }
}
