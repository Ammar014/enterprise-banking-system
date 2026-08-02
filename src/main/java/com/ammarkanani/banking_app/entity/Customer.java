package com.ammarkanani.banking_app.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true, length = 15)
    private String cnic;

    @Column(nullable = false)
    private String passwordHash;

    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

    }

    @PreUpdate
    public void preUpdate() {

        updatedAt = LocalDateTime.now();

    }

    // public Customer() {
    // }

    // public Customer(String firstName, String lastName, String email,
    // String phone, String cnic, String passwordHash,
    // LocalDate dateOfBirth, String status,
    // LocalDateTime createdAt, LocalDateTime updatedAt) {

    // this.firstName = firstName;
    // this.lastName = lastName;
    // this.email = email;
    // this.phone = phone;
    // this.cnic = cnic;
    // this.passwordHash = passwordHash;
    // this.dateOfBirth = dateOfBirth;
    // this.status = status;
    // this.createdAt = createdAt;
    // this.updatedAt = updatedAt;
    // }

    // public Long getCustomerId() {
    // return customerId;
    // }

    // public void setCustomerId(Long customerId) {
    // this.customerId = customerId;
    // }

    // public String getFirstName() {
    // return firstName;
    // }

    // public void setFirstName(String firstName) {
    // this.firstName = firstName;
    // }

    // public String getLastName() {
    // return lastName;
    // }

    // public void setLastName(String lastName) {
    // this.lastName = lastName;
    // }

    // public String getEmail() {
    // return email;
    // }

    // public void setEmail(String email) {
    // this.email = email;
    // }

    // public String getPhone() {
    // return phone;
    // }

    // public void setPhone(String phone) {
    // this.phone = phone;
    // }

    // public String getCnic() {
    // return cnic;
    // }

    // public void setCnic(String cnic) {
    // this.cnic = cnic;
    // }

    // public String getPasswordHash() {
    // return passwordHash;
    // }

    // public void setPasswordHash(String passwordHash) {
    // this.passwordHash = passwordHash;
    // }

    // public LocalDate getDateOfBirth() {
    // return dateOfBirth;
    // }

    // public void setDateOfBirth(LocalDate dateOfBirth) {
    // this.dateOfBirth = dateOfBirth;
    // }

    // public String getStatus() {
    // return status;
    // }

    // public void setStatus(String status) {
    // this.status = status;
    // }

    // public LocalDateTime getCreatedAt() {
    // return createdAt;
    // }

    // public void setCreatedAt(LocalDateTime createdAt) {
    // this.createdAt = createdAt;
    // }

    // public LocalDateTime getUpdatedAt() {
    // return updatedAt;
    // }

    // public void setUpdatedAt(LocalDateTime updatedAt) {
    // this.updatedAt = updatedAt;
    // }
}
