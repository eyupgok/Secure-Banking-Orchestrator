package org.eyup.security;

import org.eyup.model.Transaction;

public interface FraudRule {
    /**
     * İşlem şüpheliyse true döner.
     */
    boolean isFraud(Transaction transaction);
    String getRuleName();
}