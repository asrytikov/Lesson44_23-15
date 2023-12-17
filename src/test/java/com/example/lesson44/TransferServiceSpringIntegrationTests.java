package com.example.lesson44;

import com.example.lesson44.model.Account;
import com.example.lesson44.repository.AccountRepository;
import com.example.lesson44.services.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransferServiceSpringIntegrationTests {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private TransferService transferService;

    @Test
    void transferServiceTransferAmountTest(){
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        Account destination = new Account();
        destination.setId(2);
        destination.setAmount(new BigDecimal(1000));

        when(accountRepository.findById(sender.getId())).thenReturn(Optional.of(sender));
        when(accountRepository.findById(destination.getId())).thenReturn(Optional.of(destination));

        transferService.transferMoney(1,2, new BigDecimal(100));

        verify(accountRepository).changeAmount(1, new BigDecimal(900));
        verify(accountRepository).changeAmount(2, new BigDecimal(1100));
    }

}
