package org.mypet.accountservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "clientid", nullable = false)
    private UUID clientId;

    @Column(name = "account_name")
    private String accountName;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(name = "is_closed")
    boolean isClosed;

    @Column(name = "open_date")
    private LocalDateTime openDate;
}
