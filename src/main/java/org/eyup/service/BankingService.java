package org.eyup.service;

import org.eyup.model.Account;
import org.eyup.model.Transaction;
import org.eyup.model.TransactionStatus;
import org.eyup.security.FraudDetector;

import java.math.BigDecimal;

public class BankingService {

    private final FraudDetector fraudDetector;
    private final NotificationService notificationService;

    public BankingService() {
        this.fraudDetector = new FraudDetector();
        this.notificationService = new NotificationService();
    }

    // FACADE PATTERN: Dışarıya tek bir metod sunuyoruz, içeride dünyaları yönetiyoruz.
    public void transferFunds(Account from, Account to, BigDecimal amount) {
        System.out.println("\n--- İŞLEM BAŞLIYOR: " + from.getOwnerName() + " -> " + to.getOwnerName() + " (" + amount + " TL) ---");

        // 1. İşlem Kaydı Oluştur
        Transaction tx = new Transaction(from.getId(), to.getId(), amount);

        // 2. Güvenlik Kontrolü (FRAUD CHECK)
        if (fraudDetector.check(tx)) {
            System.out.println("❌ İŞLEM REDDEDİLDİ: Güvenlik riski tespit edildi!");
            notificationService.sendSms(from.getOwnerName(), "Hesabınızda şüpheli işlem engellendi.");
            return;
        }

        // 3. Para Çekme (CheckingAccount içindeki kilitli metod çalışır)
        boolean withdrawn = from.withdraw(amount);

        if (withdrawn) {
            // 4. Para Yatırma
            to.deposit(amount);
            tx.setStatus(TransactionStatus.SUCCESS);

            System.out.println("✅ TRANSFER BAŞARILI!");
            notificationService.sendSms(from.getOwnerName(), amount + " TL transferiniz gerçekleşti.");
            notificationService.sendSms(to.getOwnerName(), hesabinaParaGeldiMesaji(amount));
            notificationService.logTransactionToAudit("Transfer ID: " + tx.getId() + " - SUCCESS");
        } else {
            tx.setStatus(TransactionStatus.FAILED);
            System.out.println("❌ İŞLEM BAŞARISIZ: Yetersiz Bakiye.");
            notificationService.logTransactionToAudit("Transfer ID: " + tx.getId() + " - FAILED (Balance)");
        }
    }

    private String hesabinaParaGeldiMesaji(BigDecimal amount) {
        return "Hesabınıza " + amount + " TL giriş oldu.";
    }
}