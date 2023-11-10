package com.uom.supermarketbackend.service;

import com.uom.supermarketbackend.dto.ProductDTO;
import com.uom.supermarketbackend.model.Product;
import com.uom.supermarketbackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDTO(product))
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.map(product1 -> new ProductDTO(product1)).orElse(null);
    }

    public List<ProductDTO> searchProducts(String productName) {
        List<Product> products = productRepository.findAllByNameContainingIgnoreCase(productName);
        return products.stream()
                .map(product -> new ProductDTO(product))
                .collect(Collectors.toList());
    }

    public Product saveproducts(Product product) {
        return productRepository.save(product);
    }

    public ProductDTO updateProduct(Long productId, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            // Update the fields of the existing product with the fields of the updated product
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setDescription(updatedProduct.getDescription());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            // Set other fields as needed

            // Save the updated product
            productRepository.save(product);
            return new ProductDTO(product);
        }
        return null; // Product with the given ID not found
    }


    public List<ProductDTO> deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDTO(product))
                .collect(Collectors.toList());
    }

}
