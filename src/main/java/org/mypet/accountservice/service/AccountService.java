package org.mypet.accountservice.service;

import org.mypet.accountservice.dto.CreateAccountRequestDto;
import org.mypet.accountservice.entity.Account;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountService {
    Account createAccount(CreateAccountRequestDto requestDto, UUID clientId);

    void deleteAccount(UUID accountId);

    void performTransfer(UUID senderAccountId, UUID clientId, UUID receiverAccountId, BigDecimal amount);
}
