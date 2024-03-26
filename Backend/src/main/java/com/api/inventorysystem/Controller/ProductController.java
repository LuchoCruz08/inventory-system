package com.api.inventorysystem.Controller;
import com.api.inventorysystem.Entity.Product;
import com.api.inventorysystem.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/form")
    public ResponseEntity<String> form() {
        return ResponseEntity.ok().body("Form to save a product");
    }

    @GetMapping("/new")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product newProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getById(id);
        if(product != null) {
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product product) {
        Product productToEdit = productService.getById(id);
        if(productToEdit != null) {
            productToEdit.setProductDescription(product.getProductDescription());
            productToEdit.setProductPrice(product.getProductPrice());
            productToEdit.setProductStock(product.getProductStock());
            productToEdit.setProductBrand(product.getProductBrand());
            productToEdit.setProductSupplier(product.getProductSupplier());
            productToEdit.setProductCategory(product.getProductCategory());
            productToEdit.setImageUrl(product.getImageUrl());

            productService.updateProduct(productToEdit);
            return ResponseEntity.ok().body(productToEdit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter-price")
    public ResponseEntity<List<Product>> getByPrice(
            @RequestParam(required = false) Long id, @RequestParam(required = false) Double min,
            @RequestParam(required = false) Double max, @RequestParam(required = false) Long minStock,
            @RequestParam(required = false) Long maxStock, @RequestParam(required = false) String category
    ) {
        List<Product> productsFiltered = productService.filterPrice(id, min, max, minStock, maxStock, category);
        return ResponseEntity.ok(productsFiltered);
    }

}
