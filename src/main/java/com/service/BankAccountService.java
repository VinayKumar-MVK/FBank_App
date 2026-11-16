package com.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.BankAccount;
import com.exception.*;
import com.repository.BankAccountRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class BankAccountService {

    private final BankAccountRepository repository;

    private final Validator validator;

    public BankAccountService(
            BankAccountRepository repository,
            Validator validator) {

        this.repository = repository;
        this.validator = validator;
    }

    // Validation Method

    private void validateAccount(BankAccount account) {

        var violations = validator.validate(account);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    // 1. CREATE ACCOUNT

    public BankAccount createAccount(BankAccount account) {

        validateAccount(account);

        if (repository.existsByAccountNumber(
                account.getAccountNumber())) {

            throw new DuplicateAccountException(
                    "Account number already exists!");
        }

        return repository.save(account);
    }

    // 2. GET ALL ACCOUNTS

    public List<BankAccount> getAllAccounts() {

        return repository.findAll();
    }

    // 3. GET ACCOUNT BY ID

    public BankAccount getAccountById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                    new AccountNotFoundException(
                        "Account not found with ID: " + id));
    }

    // 4. GET ACCOUNT BY ACCOUNT NUMBER

    public BankAccount getAccountByNumber(String accountNumber) {

        return repository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                    new AccountNotFoundException(
                        "Account not found: " + accountNumber));
    }

    // 5. UPDATE ACCOUNT HOLDER DETAILS

    public BankAccount updateAccountHolder(
            Long id, String name, Integer age) {

        BankAccount account = getAccountById(id);

        account.setAccountHolderName(name);
        account.setAge(age);

        validateAccount(account);

        return repository.save(account);
    }

    // 6. UPDATE PHONE AND EMAIL

    public BankAccount updateContact(
            Long id, String phone, String email) {

        BankAccount account = getAccountById(id);

        account.setPhoneNumber(phone);
        account.setEmail(email);

        validateAccount(account);

        return repository.save(account);
    }

    // 7. DELETE ACCOUNT

    public void deleteAccount(Long id) {

        BankAccount account = getAccountById(id);

        repository.delete(account);
    }

    // 8. SEARCH BY ACCOUNT TYPE

    public List<BankAccount> getAccountsByType(String type) {

        return repository.findByAccountType(type);
    }

    // 9. DEPOSIT MONEY

    @Transactional
    public BankAccount depositMoney(
            String accountNumber, BigDecimal amount) {

        BankAccount account =
                getAccountByNumber(accountNumber);

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero");
        }

        account.setBalance(
                account.getBalance().add(amount));

        return repository.save(account);
    }

    // 10. WITHDRAW MONEY

    @Transactional
    public BankAccount withdrawMoney(
            String accountNumber, BigDecimal amount) {

        BankAccount account =
                getAccountByNumber(accountNumber);

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero");
        }

        if (account.getBalance().compareTo(amount) < 0) {

            throw new InsufficientBalanceException(
                    "Insufficient balance!");
        }

        account.setBalance(
                account.getBalance().subtract(amount));

        return repository.save(account);
    }

    // 11. TRANSFER MONEY

    @Transactional
    public void transferMoney(
            String fromAccount,
            String toAccount,
            BigDecimal amount) {

        if (fromAccount.equals(toAccount)) {

            throw new InvalidAmountException(
                    "Cannot transfer to the same account");
        }

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new InvalidAmountException(
                    "Transfer amount must be greater than zero");
        }

        BankAccount sender =
                getAccountByNumber(fromAccount);

        BankAccount receiver =
                getAccountByNumber(toAccount);

        if (sender.getBalance().compareTo(amount) < 0) {

            throw new InsufficientBalanceException(
                    "Insufficient balance for transfer!");
        }

        // Deduct money from sender

        sender.setBalance(
                sender.getBalance().subtract(amount));

        // Add money to receiver

        receiver.setBalance(
                receiver.getBalance().add(amount));

        // Save both accounts

        repository.save(sender);

        repository.save(receiver);

        System.out.println("Money transferred successfully!");
    }

    // 12. CUSTOM FINDER METHODS

    public List<BankAccount> getAccountsByBalance(
            BigDecimal amount) {

        return repository.findByBalanceGreaterThan(amount);
    }

    public List<BankAccount> getAccountsBetweenBalance(
            BigDecimal min, BigDecimal max) {

        return repository.findByBalanceBetween(min, max);
    }

    public List<BankAccount> sortByBalance() {

        return repository.findAllByOrderByBalanceDesc();
    }

    public boolean emailExists(String email) {

        return repository.existsByEmail(email);
    }

    public long countSavingsAccounts() {

        return repository.countByAccountType("SAVINGS");
    }

}
