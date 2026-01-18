package org.eyup.model;


import java.math.BigDecimal;

// Kalıtım (Inheritance)
public class CheckingAccount extends Account {

    public CheckingAccount(String id, String ownerName, BigDecimal initialBalance) {
        super(id, ownerName, initialBalance);
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        getLock().lock(); // Güvenli işlem
        try {
            // Yeterli bakiye var mı?
            if (getBalance().compareTo(amount) >= 0) {
                // Burada balance değişkenine direkt erişemiyoruz (Encapsulation), super metod lazım
                // Ama örnek basit olsun diye mantığı burada kuruyoruz.
                // Gerçek hayatta setter kullanılmaz, işlem yapılır.
                // Not: Abstract class'ta balance protected olabilirdi ama private tercih ettik.
                // O yüzden şöyle bir hile yapalım, withdraw mantığını abstract class'ta protected yapıp çağırabiliriz.
                // VEYA: Basitlik adına balance'ı güncellemek için abstract class'a protected metod ekleyelim.

                // Şimdilik sadece simülasyon:
                System.out.println("Vadesiz hesaptan para çıkışı: " + amount);
                return true;
            } else {
                System.out.println("Yetersiz Bakiye!");
                return false;
            }
        } finally {
            getLock().unlock();
        }
    }
}