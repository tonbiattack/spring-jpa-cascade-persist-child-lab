package jp.tonbiattack.debuglab.catalog;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
class CatalogRepositoryTest {
    @Autowired CatalogRepository repository;
    @Autowired TransactionTemplate transactionTemplate;
    @Autowired EntityManager entityManager;

    @Test
    void persistsNewItemsWhenSavingCatalog() {
        Long id = assertDoesNotThrow(() -> transactionTemplate.execute(status -> {
            Catalog catalog = new Catalog("catalog-001");
            catalog.addItem("tea-001");
            return repository.saveAndFlush(catalog).getId();
        }), "親を保存すると新規子も永続化される");
        Integer itemCount = transactionTemplate.execute(status -> {
            entityManager.clear();
            return repository.findById(id).orElseThrow().getItems().size();
        });
        assertEquals(1, itemCount, "再読込した親は保存済みの子を一件返す");
    }
}
