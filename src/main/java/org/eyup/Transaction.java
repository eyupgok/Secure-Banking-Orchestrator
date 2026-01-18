package org.eyup;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final String id;
    private final String sourceAccountId;
    private final String targetAccountId;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;
    private TransactionStatus status;

    public Transaction(String sourceAccountId, String targetAccountId, BigDecimal amount) {
        this.id = UUID.randomUUID().toString(); // Benzersiz ID
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.status = TransactionStatus.PENDING;
    }

    // Getter ve Setterlar (Encapsulation)
    public String getId() { return id; }
    public String getSourceAccountId() { return sourceAccountId; }
    public String getTargetAccountId() { return targetAccountId; }
    public BigDecimal getAmount() { return amount; }
    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }
}