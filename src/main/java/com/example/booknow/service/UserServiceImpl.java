package com.example.booknow.service;

import com.example.booknow.entity.Bill;
import com.example.booknow.entity.User;
import com.example.booknow.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo userRepo;

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public long saveUser(User user) {
        User savedUser = userRepo.save(user);
        return savedUser.getId();
    }

    @Override
    public List<Bill> getAllBillsForUser(long userId) {
        if(!userRepo.existsById(userId)){
            return null;
        }
        User user = userRepo.getById(userId);
        List<Bill> billList = user.getBillList();
        return billList;
    }



}
