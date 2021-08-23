package com.exercise.vendingmachine.controller;

import com.exercise.vendingmachine.dto.ProductDto;
import com.exercise.vendingmachine.dto.VendingMachineUserDetailsDto;
import com.exercise.vendingmachine.model.Product;
import com.exercise.vendingmachine.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/products",
        produces= MediaType.APPLICATION_JSON_VALUE,
        consumes=MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    private @ResponseBody
    Product createProduct(@AuthenticationPrincipal VendingMachineUserDetailsDto userDetailsDto,
            @RequestBody @Valid ProductDto productDto) {
        return productService.createProduct(userDetailsDto, productDto);
    }

    @GetMapping("/{productId}")
    private @ResponseBody
    Product getProduct(@AuthenticationPrincipal VendingMachineUserDetailsDto userDetailsDto,
                       @PathVariable Long productId) {
        return productService.getProduct(userDetailsDto, productId);
    }

    @PutMapping("/{productId}")
    private @ResponseBody
    Product updateProduct(@AuthenticationPrincipal VendingMachineUserDetailsDto userDetailsDto,
                          @PathVariable Long productId, @RequestBody @Valid ProductDto productDto) {
        return productService.updateProduct(userDetailsDto, productId, productDto);
    }

    @DeleteMapping("/{productId}")
    private @ResponseBody
    Product deleteProduct(@AuthenticationPrincipal VendingMachineUserDetailsDto userDetailsDto,
                          @PathVariable Long productId) {
        return productService.deleteProduct(userDetailsDto, productId);
    }

}
