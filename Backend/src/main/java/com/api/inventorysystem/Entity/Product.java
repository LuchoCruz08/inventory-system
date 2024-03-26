package com.api.inventorysystem.Entity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price", nullable = false)
    private double productPrice;

    @Column(name = "product_stock")
    private Long productStock;

    @Column(name = "product_brand")
    private String productBrand;

    @Column(name = "product_supplier")
    private String productSupplier;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "product_category")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category productCategory;

    @Column(name = "image_url", length = 1000)
    private String imageUrl;

}
