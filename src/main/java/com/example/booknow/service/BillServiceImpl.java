package com.example.booknow.service;

import com.example.booknow.entity.Bill;
import com.example.booknow.entity.User;
import com.example.booknow.repository.BillRepo;
import com.example.booknow.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService{
    @Autowired
    BillRepo billRepo;
    @Autowired
    UserRepo userRepo;
    @Override
    public long addBill(long userId, Bill bill) {
        if(!userRepo.existsById(userId)){
            return -1;
        }
        User user = userRepo.findById(userId).get();
        bill.setUser(user);
        Bill savedBill = billRepo.save(bill);
        user.getBillList().add(savedBill);
        return savedBill.getBillId();
    }

    @Override
    public Bill updateBill(long billId,Bill updatedBill) {
        return billRepo.findById(billId).map(
                element ->{
                    element.setAmount(updatedBill.getAmount());
                    element.setType(updatedBill.getType());
                    element.setName(updatedBill.getName());
                    return billRepo.save(element);
                }
        )
                .orElseGet(()->{
                    return null;
                });

    }
}
