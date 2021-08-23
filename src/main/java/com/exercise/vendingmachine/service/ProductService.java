package com.exercise.vendingmachine.service;

import com.exercise.vendingmachine.dto.ProductDto;
import com.exercise.vendingmachine.dto.VendingMachineUserDetailsDto;
import com.exercise.vendingmachine.model.Product;

public interface ProductService {

    Product createProduct(VendingMachineUserDetailsDto userDetailsDto, ProductDto productDto);

    Product getProduct(VendingMachineUserDetailsDto userDetailsDto, Long productId);

    Product updateProduct(VendingMachineUserDetailsDto userDetailsDto, Long productId, ProductDto productDto);

    Product deleteProduct(VendingMachineUserDetailsDto userDetailsDto, Long productId);

}
