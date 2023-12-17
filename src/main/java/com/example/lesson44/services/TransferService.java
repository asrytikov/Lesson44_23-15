package com.example.lesson44.services;

import com.example.lesson44.exceptions.AccountNotFoundException;
import com.example.lesson44.model.Account;
import com.example.lesson44.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount){
        Account sender = accountRepository.findById(idSender)
                .orElseThrow(()->new AccountNotFoundException());
        Account receiver = accountRepository.findById(idReceiver)
                .orElseThrow(()->new AccountNotFoundException());
        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal reciverNewAmount = receiver.getAmount().add(amount);

        accountRepository.changeAmount(idSender, senderNewAmount);
        accountRepository.changeAmount(idReceiver, reciverNewAmount);
    }

    public Iterable<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public List<Account> findAccountsByName(String name){
        return accountRepository.findAccountByName(name);
    }
}
