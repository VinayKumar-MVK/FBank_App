package com.entity;
import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Bank_details")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @NotBlank(message = "Account number is required")
    @Column(unique = true, nullable = false)
    private String accountNumber;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Minimum 3 characters required")
    private String accountHolderName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must contain 10 digits")
    private String phoneNumber;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be 18 or above")
    private Integer age;

    @NotBlank(message = "Account type is required")
    @Pattern(regexp = "SAVINGS|CURRENT",
            message = "Account type must be SAVINGS or CURRENT")
    private String accountType;

    @NotNull(message = "Balance is required")
    @DecimalMin(value = "0.0",
            message = "Balance cannot be negative")
    private BigDecimal balance;

    public BankAccount() {
    }

    public BankAccount(String accountNumber,
            String accountHolderName,
            String email,
            String phoneNumber,
            Integer age,
            String accountType,
            BigDecimal balance) {

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Getters and Setters

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount [accountId=" + accountId
                + ", accountNumber=" + accountNumber
                + ", accountHolderName=" + accountHolderName
                + ", email=" + email
                + ", phoneNumber=" + phoneNumber
                + ", age=" + age
                + ", accountType=" + accountType
                + ", balance=" + balance + "]";
    }
}