package com.exercise.vendingmachine.service.impl;

import com.exercise.vendingmachine.dto.DepositDto;
import com.exercise.vendingmachine.dto.UserDto;
import com.exercise.vendingmachine.dto.VendingMachineUserDetailsDto;
import com.exercise.vendingmachine.model.User;
import com.exercise.vendingmachine.repository.UserRepository;
import com.exercise.vendingmachine.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .deposit(userDto.getDeposit())
                .role(userDto.getRole())
                .build();
        return this.userRepository.save(user);
    }

    @Override
    public User getUser(VendingMachineUserDetailsDto userDetailsDto, Long userId) {
        checkUserPermission(userDetailsDto, userId);
        return this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User updateUser(VendingMachineUserDetailsDto userDetailsDto, Long userId, UserDto userDto) {
        checkUserPermission(userDetailsDto, userId);
        User user = this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.setUsername(userDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setDeposit(userDto.getDeposit());
        user.setRole(userDto.getRole());
        return this.userRepository.save(user);
    }

    @Override
    public User deleteUser(VendingMachineUserDetailsDto userDetailsDto, Long userId) {
        checkUserPermission(userDetailsDto, userId);
        User user = this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        this.userRepository.delete(user);
        return user;
    }

    @Transactional
    public User deposit(VendingMachineUserDetailsDto userDetailsDto, DepositDto depositDto) {
        User user = this.userRepository.findById(userDetailsDto.getUser().getId()).orElseThrow(EntityNotFoundException::new);
        if (user.getDeposit() == null) {
            user.setDeposit(0L);
        }
        user.setDeposit(user.getDeposit() + depositDto.getCoin().getCents());
        return this.userRepository.save(user);
    }

    @Transactional
    public User reset(VendingMachineUserDetailsDto userDetailsDto) {
        User user = this.userRepository.findById(userDetailsDto.getUser().getId()).orElseThrow(EntityNotFoundException::new);
        user.setDeposit(0L);
        return this.userRepository.save(user);
    }

    /*
     * Strict security policy applied, users can only get or modify their own accounts
     */
    private static void checkUserPermission(VendingMachineUserDetailsDto userDetailsDto, Long userId) {
        if (!userDetailsDto.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Access denied");
        }
    }

}
