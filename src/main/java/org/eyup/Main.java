package org.eyup;

import org.eyup.model.Account;
import org.eyup.model.CheckingAccount;
import org.eyup.service.BankingService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // 1. Servisi Başlat
        BankingService bank = new BankingService();

        // 2. Kullanıcıları ve Hesapları Oluştur
        Account ahmetHesap = new CheckingAccount("101", "Ahmet Yılmaz", new BigDecimal("5000"));
        Account mehmetHesap = new CheckingAccount("102", "Mehmet Demir", new BigDecimal("2000"));

        // SENARYO 1: Başarılı Transfer (1000 TL)
        bank.transferFunds(ahmetHesap, mehmetHesap, new BigDecimal("1000"));

        // SENARYO 2: Yetersiz Bakiye Denemesi (10.000 TL)
        bank.transferFunds(ahmetHesap, mehmetHesap, new BigDecimal("10000"));

        // SENARYO 3: Şüpheli İşlem - Fraud (60.000 TL) -> Limitimiz 50.000 idi
        // Bakiyesi olsa bile FraudDetector bunu yakalamalı!
        // Hile yapıp Ahmet'e sanal para ekleyelim ki bakiyesi yetiyor gibi olsun
        ahmetHesap.deposit(new BigDecimal("100000"));
        bank.transferFunds(ahmetHesap, mehmetHesap, new BigDecimal("60000"));
    }
}