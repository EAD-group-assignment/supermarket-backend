package com.uom.supermarketbackend.controller;

import com.uom.supermarketbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /*
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return ResponseEntity.ok("Product added successfully");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductDetails(@PathVariable Long productId) {
        ProductDTO productDTO = productService.getProductDetails(productId);
        return ResponseEntity.ok(productDTO);
    }

    @PatchMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductUpdateDTO productUpdateDTO) {
        productService.updateProduct(productId, productUpdateDTO);
        return ResponseEntity.ok("Product information updated successfully");
    }
    */
}
