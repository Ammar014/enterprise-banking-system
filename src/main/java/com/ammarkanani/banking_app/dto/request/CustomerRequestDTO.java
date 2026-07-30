package com.ammarkanani.banking_app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;


public class CustomerRequestDTO {

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Email(message = "Invalid Email Format")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;

    @Pattern(
        regexp = "^03\\d{9}$",
        message = "Phone Number must be 11 digits and start with 03"
    )
    private String phoneNumber;

    @Pattern(
        regexp = "^\\d{5}-\\d{7}-\\d$",
        message = "Invalid CNIC Format"
    )
    private String cnic;

    public CustomerRequestDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }
}
