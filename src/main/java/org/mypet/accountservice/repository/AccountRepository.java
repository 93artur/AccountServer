package org.mypet.accountservice.repository;

import org.mypet.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByAccountIdAndClientId(UUID accountId, UUID clientId);

    @Modifying
    @Query("UPDATE Account a SET a.balance = ?2 WHERE a.accountId = ?1")
    void setBalance(UUID accountId, BigDecimal balance);
}
