package com.example.lesson44.controllers;

import com.example.lesson44.model.Account;
import com.example.lesson44.model.TransferRequest;
import com.example.lesson44.services.TransferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public void transferMoney(
            @RequestBody TransferRequest request
            ){
        transferService.transferMoney(
                request.getSenderId(),
                request.getReceiveId(),
                request.getAmount());
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(
            @RequestParam(required = false) String name
    ){
        if (name==null){
        return transferService.getAllAccounts();
        }else{
            return transferService.findAccountsByName(name);
        }
    }

}
