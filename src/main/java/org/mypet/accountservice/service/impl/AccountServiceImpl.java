package org.mypet.accountservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mypet.accountservice.dto.CreateAccountRequestDto;
import org.mypet.accountservice.entity.Account;
import org.mypet.accountservice.exception.AccountIsClosedException;
import org.mypet.accountservice.exception.AccountNotFoundException;
import org.mypet.accountservice.exception.NotEnoughBalanceException;
import org.mypet.accountservice.repository.AccountRepository;
import org.mypet.accountservice.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public void performTransfer(UUID senderAccountId, UUID clientId, UUID receiverAccountId, BigDecimal amount) {
        Account sender = accountRepository.findByAccountIdAndClientId(senderAccountId, clientId)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format("Account with sender-id %s not found", senderAccountId)));
        Account receiver = accountRepository.findById(receiverAccountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format("Account with receiver-id %s not found", receiverAccountId)));

        checkBalance(sender, receiver, amount);

        BigDecimal senderResultBalance = sender.getBalance().subtract(amount);
        BigDecimal receiverResultBalance = receiver.getBalance().add(amount);

        accountRepository.setBalance(sender.getAccountId(), senderResultBalance);
        accountRepository.setBalance(receiver.getAccountId(), receiverResultBalance);
    }

    @Override
    public void deleteAccount(UUID accountId) {

    }

    @Override
    public Account createAccount(CreateAccountRequestDto requestDto, UUID clientId) {
        return null;
    }

    private void checkBalance(Account sender, Account receiver, BigDecimal amount) {
        if (sender.isClosed() || receiver.isClosed()) {
            throw new AccountIsClosedException();
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new NotEnoughBalanceException();
        }
    }
}
