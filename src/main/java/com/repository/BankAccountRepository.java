package com.repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.BankAccount;

public interface BankAccountRepository
        extends JpaRepository<BankAccount, Long> {

    // Find account by account number
    Optional<BankAccount> findByAccountNumber(String accountNumber);

    // Find accounts by account type
    List<BankAccount> findByAccountType(String accountType);

    // Find accounts with balance greater than given amount
    List<BankAccount> findByBalanceGreaterThan(BigDecimal amount);

    // Find accounts by holder name
    List<BankAccount> findByAccountHolderName(String name);

    // Find accounts between two balances
    List<BankAccount> findByBalanceBetween(
            BigDecimal min, BigDecimal max);

    // Check email exists
    boolean existsByEmail(String email);

    // Sort accounts by balance descending
    List<BankAccount> findAllByOrderByBalanceDesc();

    // Check duplicate account number
    boolean existsByAccountNumber(String accountNumber);

    // Count savings accounts
    long countByAccountType(String accountType);

}