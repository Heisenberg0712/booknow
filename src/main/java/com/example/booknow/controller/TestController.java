package com.example.booknow.controller;

import com.example.booknow.entity.Bill;
import com.example.booknow.entity.User;
import com.example.booknow.service.BillService;
import com.example.booknow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TestController {
    @Autowired
    UserService userService;
    @Autowired
    BillService billService;

    @PostMapping("/user")
    public ResponseEntity<Long> postUser(@RequestBody User user){

        try{
            long savedUserId = userService.saveUser(user);
            return new ResponseEntity<>(savedUserId,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        try {
            List<User> userList = userService.getAllUser();
            return new ResponseEntity<>(userList,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Bill>> getAllBills(@PathVariable("id") long id){
        try {
            List<Bill> billList = userService.getAllBillsForUser(id);
            if(billList.size()==0){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); //no bills exist
            }
            return new ResponseEntity<>(billList,HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/bill/{id}")
    public ResponseEntity<String> postBill(@PathVariable("id") long id, @RequestBody Bill bill){

        try {
           long exists = billService.addBill(id,bill);
           if(exists==-1){
               return new ResponseEntity<>("user not found",HttpStatus.NO_CONTENT); //no user exist for this id
           }
           return new ResponseEntity<>("Bill added for the respective user",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updatebill/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable("id") long id,@RequestBody Bill bill){
        try{
            Bill updatedBill = billService.updateBill(id,bill);
            if(updatedBill==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); //if bill id not found then return the 404 error
            }
            return new ResponseEntity<>(updatedBill,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
