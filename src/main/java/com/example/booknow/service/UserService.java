package com.example.booknow.service;

import com.example.booknow.entity.Bill;
import com.example.booknow.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();
    long saveUser(User user);
    List<Bill> getAllBillsForUser(long userId);


}
