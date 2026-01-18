package org.eyup.service;

public class NotificationService {

    public void sendSms(String phoneNumber, String message) {
        // Gerçek hayatta burada Turkcell/Vodafone API'si olur.
        System.out.println("📨 [SMS GÖNDERİLDİ] -> " + phoneNumber + ": " + message);
    }

    public void logTransactionToAudit(String message) {
        System.out.println("💾 [AUDIT LOG] -> " + message);
    }
}