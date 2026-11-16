package com.runner;


import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.entity.BankAccount;
import com.service.BankAccountService;

@Component
public class BankAccountRunner implements CommandLineRunner {

    private final BankAccountService service;

    public BankAccountRunner(BankAccountService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {

        try {

            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");

            // CREATE ACCOUNTS

            BankAccount a1 = new BankAccount(
                    "ACC101",
                    "Vinay Kumar",
                    "vinay@gmail.com",
                    "9876543210",
                    22,
                    "SAVINGS",
                    new BigDecimal("10000")
            );

            BankAccount a2 = new BankAccount(
                    "ACC102",
                    "Rahul Kumar",
                    "rahul@gmail.com",
                    "9876543211",
                    25,
                    "CURRENT",
                    new BigDecimal("15000")
            );

            BankAccount a3 = new BankAccount(
                    "ACC103",
                    "Priya Sharma",
                    "priya@gmail.com",
                    "9876543212",
                    23,
                    "SAVINGS",
                    new BigDecimal("20000")
            );

            service.createAccount(a1);
            service.createAccount(a2);
            service.createAccount(a3);

            System.out.println("\nAccounts created successfully!");

            // DISPLAY ALL ACCOUNTS

            System.out.println("\n===== ALL ACCOUNTS =====");

            service.getAllAccounts().forEach(System.out::println);

            // SEARCH BY ACCOUNT NUMBER

            System.out.println("\n===== SEARCH ACCOUNT =====");

            System.out.println(
                    service.getAccountByNumber("ACC101"));

            // DEPOSIT

            System.out.println("\n===== DEPOSIT =====");

            service.depositMoney(
                    "ACC101",
                    new BigDecimal("5000"));

            System.out.println("5000 deposited successfully!");

            // WITHDRAW

            System.out.println("\n===== WITHDRAW =====");

            service.withdrawMoney(
                    "ACC101",
                    new BigDecimal("2000"));

            System.out.println("2000 withdrawn successfully!");

            // TRANSFER

            System.out.println("\n===== TRANSFER =====");

            service.transferMoney(
                    "ACC101",
                    "ACC102",
                    new BigDecimal("3000"));

            // DISPLAY UPDATED ACCOUNTS

            System.out.println("\n===== UPDATED ACCOUNTS =====");

            service.getAllAccounts().forEach(System.out::println);

            // SEARCH BY ACCOUNT TYPE

            System.out.println("\n===== SAVINGS ACCOUNTS =====");

            service.getAccountsByType("SAVINGS")
                    .forEach(System.out::println);

            // CUSTOM FINDER

            System.out.println("\n===== BALANCE ABOVE 10000 =====");

            service.getAccountsByBalance(
                    new BigDecimal("10000"))
                    .forEach(System.out::println);

            // COUNT SAVINGS ACCOUNTS

            System.out.println("\n===== SAVINGS ACCOUNT COUNT =====");

            System.out.println(
                    service.countSavingsAccounts());

            // SORT BY BALANCE

            System.out.println("\n===== SORT BY BALANCE =====");

            service.sortByBalance()
                    .forEach(System.out::println);

            // INVALID WITHDRAWAL

            System.out.println("\n===== INVALID WITHDRAWAL =====");

            try {

                service.withdrawMoney(
                        "ACC101",
                        new BigDecimal("50000"));

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }

            // INVALID ACCOUNT

            System.out.println("\n===== INVALID ACCOUNT =====");

            try {

                service.getAccountByNumber("ACC999");

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }

            // INVALID EMAIL

            System.out.println("\n===== VALIDATION TEST =====");

            try {

                BankAccount invalid = new BankAccount(
                        "ACC104",
                        "Arun Kumar",
                        "abc@gmail",
                        "12345",
                        16,
                        "SAVINGS",
                        new BigDecimal("-500")
                );

                service.createAccount(invalid);

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }

            System.out.println("\n===== APPLICATION COMPLETED =====");

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}