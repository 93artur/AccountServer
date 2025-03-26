package org.mypet.accountservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
