package org.eyup;


public enum TransactionStatus {
    PENDING,    // Beklemede
    SUCCESS,    // Başarılı
    FAILED,     // Hata (Bakiye yetersiz vb.)
    FRAUD,      // Şüpheli işlem yakalandı
    BLOCKED     // Bloke edildi
}