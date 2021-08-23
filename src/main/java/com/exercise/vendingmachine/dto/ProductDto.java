package com.exercise.vendingmachine.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Data
public class ProductDto {

    @NotNull
    public Integer amountAvailable;

    @NotNull
    public BigInteger cost;

    @Size(min = 2, max = 32)
    public String productName;

}
