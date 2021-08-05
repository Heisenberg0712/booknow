package com.example.booknow.service;

import com.example.booknow.entity.Bill;
import com.example.booknow.repository.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;

public interface BillService {

    long addBill(long userId, Bill bill);
    Bill updateBill(long billId,Bill updatedBill);
}
