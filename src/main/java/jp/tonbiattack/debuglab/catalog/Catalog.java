package jp.tonbiattack.debuglab.catalog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String catalogCode;
    @OneToMany(mappedBy = "catalog")
    private List<CatalogItem> items = new ArrayList<>();

    protected Catalog() {}
    public Catalog(String catalogCode) { this.catalogCode = catalogCode; }
    public void addItem(String sku) {
        CatalogItem item = new CatalogItem(sku);
        item.assignCatalog(this);
        items.add(item);
    }
    public Long getId() { return id; }
    public List<CatalogItem> getItems() { return items; }
}
