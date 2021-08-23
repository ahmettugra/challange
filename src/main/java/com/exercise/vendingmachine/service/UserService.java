package com.exercise.vendingmachine.service;

import com.exercise.vendingmachine.dto.DepositDto;
import com.exercise.vendingmachine.dto.UserDto;
import com.exercise.vendingmachine.dto.VendingMachineUserDetailsDto;
import com.exercise.vendingmachine.model.User;

public interface UserService {

    User createUser(UserDto userDto);

    User getUser(VendingMachineUserDetailsDto userDetailsDto, Long userId);

    User updateUser(VendingMachineUserDetailsDto userDetailsDto, Long userId, UserDto userDto);

    User deleteUser(VendingMachineUserDetailsDto userDetailsDto, Long userId);

    User deposit(VendingMachineUserDetailsDto userDetailsDto, DepositDto depositDto);

    User reset(VendingMachineUserDetailsDto userDetailsDto);

}
