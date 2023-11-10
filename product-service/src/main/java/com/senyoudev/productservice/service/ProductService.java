package com.senyoudev.productservice.service;


import com.senyoudev.productservice.dto.ProductRequest;
import com.senyoudev.productservice.dto.ProductResponse;
import com.senyoudev.productservice.exception.ProductCreationException;
import com.senyoudev.productservice.model.Product;
import com.senyoudev.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        try {
            if (productRequest == null) {
                log.error("Invalid product request: null");
                throw new IllegalArgumentException("Invalid product request: null");
            }
            Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .build();

            productRepository.save(product);
            log.info("Product created: {}", product);
        } catch (Exception e) {
            log.error("Error while creating product", e);
            throw new ProductCreationException("Error creating product: " + e.getMessage());
        }
    }

    public List<ProductResponse> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            log.info("Products found: {}", products);
            return products.stream()
                    .map(this::mapToProductResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error while getting products", e);
            throw new ProductCreationException("Error getting products: " + e.getMessage());
        }
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
