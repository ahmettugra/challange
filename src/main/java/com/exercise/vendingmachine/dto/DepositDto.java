package com.exercise.vendingmachine.dto;

import com.exercise.vendingmachine.enumeration.CoinEnum;

import javax.validation.constraints.NotNull;

public class DepositDto {

    @NotNull
    private CoinEnum coin;

    public CoinEnum getCoin() {
        return coin;
    }

    public void setCoin(CoinEnum coin) {
        this.coin = coin;
    }

}
