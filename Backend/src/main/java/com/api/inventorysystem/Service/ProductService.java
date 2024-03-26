package com.api.inventorysystem.Service;
import com.api.inventorysystem.Entity.Product;
import com.api.inventorysystem.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No product found with ID: " + id));
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> filterPrice(Long id, Double min, Double max, Long minStock, Long maxStock, String category) {
        return productRepository.findAll().stream()
                .filter(product -> (id == null || product.getId().equals(id)))
                .filter(product -> (min == null || product.getProductPrice() >= min))
                .filter(product -> (max == null || product.getProductPrice() <= max))
                .filter(product -> (minStock == null || product.getProductStock() >= minStock))
                .filter(product -> (maxStock == null || product.getProductStock() <= maxStock))
                .filter(product -> (category == null || product.getProductCategory().getCategoryName().equals(category)))
                .collect(Collectors.toList());
    }
}
