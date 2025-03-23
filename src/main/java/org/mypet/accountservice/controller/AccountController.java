package org.mypet.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.mypet.accountservice.service.AccountService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PatchMapping("/{balance}")
    public void makeTransfer(@RequestParam("sender_account_id") String senderAccountId,
                             @RequestParam("recipient_account_id") String recipientAccountId,
                             @RequestHeader String clientId,
                             @PathVariable("balance") String balance) {
        accountService.performTransfer(UUID.fromString(senderAccountId), UUID.fromString(clientId),
                UUID.fromString(recipientAccountId), new BigDecimal(balance));
    }
}
