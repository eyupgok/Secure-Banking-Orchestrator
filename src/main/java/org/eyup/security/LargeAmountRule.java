package org.eyup.security;

import org.eyup.model.Transaction;
import java.math.BigDecimal;

public class LargeAmountRule implements FraudRule {

    private static final BigDecimal LIMIT = new BigDecimal("50000"); // 50.000 TL Limiti

    @Override
    public boolean isFraud(Transaction transaction) {
        // Eğer işlem tutarı limitten büyükse -> Şüpheli!
        return transaction.getAmount().compareTo(LIMIT) > 0;
    }

    @Override
    public String getRuleName() {
        return "LargeAmountRule";
    }
}