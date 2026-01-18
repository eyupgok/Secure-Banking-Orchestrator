package org.eyup.security;

import org.eyup.model.Transaction;
import org.eyup.model.TransactionStatus;
import java.util.ArrayList;
import java.util.List;

public class FraudDetector {

    private final List<FraudRule> rules;

    public FraudDetector() {
        this.rules = new ArrayList<>();
        // Kuralları buraya ekliyoruz (Strategy Pattern Uygulaması)
        rules.add(new LargeAmountRule());
        // İleride buraya 'FrequentTransactionRule' gibi yenilerini ekleyebilirsin.
    }

    public boolean check(Transaction transaction) {
        for (FraudRule rule : rules) {
            if (rule.isFraud(transaction)) {
                System.out.println("FRAUD DETECTED! Rule: " + rule.getRuleName() + ", ID: " + transaction.getId());
                transaction.setStatus(TransactionStatus.FRAUD);
                return true; // Şüpheli bulundu, işlemi durdur
            }
        }
        return false; // Temiz
    }
}