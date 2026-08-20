package jp.tonbiattack.debuglab.catalog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CatalogItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    @ManyToOne
    private Catalog catalog;

    protected CatalogItem() {}
    CatalogItem(String sku) { this.sku = sku; }
    void assignCatalog(Catalog catalog) { this.catalog = catalog; }
    public String getSku() { return sku; }
}
