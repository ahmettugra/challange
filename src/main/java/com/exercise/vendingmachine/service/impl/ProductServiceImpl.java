package com.exercise.vendingmachine.service.impl;

import com.exercise.vendingmachine.dto.ProductDto;
import com.exercise.vendingmachine.dto.VendingMachineUserDetailsDto;
import com.exercise.vendingmachine.model.Product;
import com.exercise.vendingmachine.repository.ProductRepository;
import com.exercise.vendingmachine.service.ProductService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(VendingMachineUserDetailsDto userDetailsDto, ProductDto productDto) {
        Product product = Product.builder()
                .amountAvailable(productDto.getAmountAvailable())
                .cost(productDto.getCost())
                .productName(productDto.getProductName())
                .sellerId(userDetailsDto.getUser().getId())
                .build();
        return this.productRepository.save(product);
    }

    public Product getProduct(VendingMachineUserDetailsDto userDetailsDto, Long productId) {
        return this.productRepository.findByIdAndSellerId(productId, userDetailsDto.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    public Product updateProduct(VendingMachineUserDetailsDto userDetailsDto, Long productId, ProductDto productDto) {
        Product product = this.productRepository.findByIdAndSellerId(productId, userDetailsDto.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        product.setAmountAvailable(productDto.getAmountAvailable());
        product.setCost(productDto.getCost());
        product.setProductName(productDto.getProductName());
        return this.productRepository.save(product);
    }

    public Product deleteProduct(VendingMachineUserDetailsDto userDetailsDto, Long productId) {
        Product product = this.productRepository.findByIdAndSellerId(productId, userDetailsDto.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        this.productRepository.delete(product);
        return product;
    }

}
