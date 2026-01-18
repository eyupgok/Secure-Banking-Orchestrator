package org.eyup.model;


import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

// Abstract Class: Soyutlama (Abstraction)
public abstract class Account {
    private String id;
    private String ownerName;
    private BigDecimal balance;

    // Thread-Safe (Eşzamanlılık) için kilit mekanizması
    private final ReentrantLock lock = new ReentrantLock();

    public Account(String id, String ownerName, BigDecimal initialBalance) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    // Para yatırma (Ortak metod)
    public void deposit(BigDecimal amount) {
        lock.lock(); // Hesabı kilitle (Başkası dokunamasın)
        try {
            this.balance = this.balance.add(amount);
            System.out.println(ownerName + " hesabına " + amount + " TL yattı. Yeni Bakiye: " + balance);
        } finally {
            lock.unlock(); // Kilidi aç
        }
    }

    // Para Çekme (Soyut metod - Alt sınıflar dolduracak)
    public abstract boolean withdraw(BigDecimal amount);

    // Bakiyeyi güvenli okuma
    public BigDecimal getBalance() {
        return balance;
    }
    // Account.java içine eklenecek:
    protected void decreaseBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public String getId() { return id; }
    public String getOwnerName() { return ownerName; }
    public ReentrantLock getLock() { return lock; }
}