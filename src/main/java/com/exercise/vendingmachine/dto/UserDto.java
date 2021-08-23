package com.exercise.vendingmachine.dto;

import com.exercise.vendingmachine.enumeration.UserRole;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Data
public class UserDto {

    @Size(min = 2, max = 32)
    public String username;

    @Size(min = 6, max = 32)
    public String password;

    public Long deposit;

    @NotNull
    public UserRole role;

}
