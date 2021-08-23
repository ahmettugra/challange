package com.exercise.vendingmachine.controller;

import com.exercise.vendingmachine.dto.DepositDto;
import com.exercise.vendingmachine.dto.VendingMachineUserDetailsDto;
import com.exercise.vendingmachine.model.User;
import com.exercise.vendingmachine.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
        produces= MediaType.APPLICATION_JSON_VALUE,
        consumes=MediaType.APPLICATION_JSON_VALUE)
public class VendingMachineController {

    private final UserService userService;

    public VendingMachineController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/deposit")
    private @ResponseBody
    User deposit(@AuthenticationPrincipal VendingMachineUserDetailsDto userDetailsDto,
            @RequestBody @Valid DepositDto depositDto) {
        return userService.deposit(userDetailsDto, depositDto);
    }

    @PostMapping("/buy")
    private @ResponseBody
    User buy(@AuthenticationPrincipal VendingMachineUserDetailsDto userDetailsDto) {
        // Not implemented yet
        return null;
    }

    @PostMapping("/reset")
    private @ResponseBody
    User reset(@AuthenticationPrincipal VendingMachineUserDetailsDto userDetailsDto) {
        return userService.reset(userDetailsDto);
    }

}
