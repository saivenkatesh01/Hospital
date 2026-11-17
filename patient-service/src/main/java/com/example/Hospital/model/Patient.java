package com.example.Hospital.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @NotBlank(message = "Patient name is required")
    @Size(min = 2, max = 100, message = "Patient name should be between 2 and 100 characters.")
    private String patientName;

    @NotBlank(message = "Patient Address cannot be empty.")
    @Size(max = 250, message ="Patient Address cannot exceed 250 characters")
    private String patientAddress;

    @NotBlank(message = "Disease cannot be empty")
    private String disease;

    @NotBlank(message = "Patient phone number is required")
    @Pattern(
    regexp = "^[6-9]\\d{9}$",
    message = "Enter a valid 10-digit Indian mobile number"
    )
    private String patientPhoneNumber;

    @NotNull(message = "Patient balance is required")
    @PositiveOrZero(message = "Balance cannot be negative")
    private Long patientBalance;

    @Past(message = "Date of birth must be in past or present")
    private LocalDate patientDOB;

    @Past(message = "Date of Admission must be in past or present")
    private LocalDate patientAdmissionDate;

    public Patient(Integer patientId, String patientName, String patientAddress, String disease, String patientPhoneNumber,
            Long patientBalance, LocalDate patientDOB, LocalDate patientAdmissionDate) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.disease = disease;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientBalance = patientBalance;
        this.patientDOB = patientDOB;
        this.patientAdmissionDate = patientAdmissionDate;
    }
    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public String getPatientAddress() {
        return patientAddress;
    }
    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }
    public String getPatientDisease() {
        return disease;
    }
    public void setPatientDisease(String disease) {
        this.disease = disease;
    }
    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }
    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }
    public Long getPatientBalance() {
        return patientBalance;
    }
    public void setPatientBalance(Long patientBalance) {
        this.patientBalance = patientBalance;
    }
    public LocalDate getPatientDOB() {
        return patientDOB;
    }
    public void setPatientDOB(LocalDate patientDOB) {
        this.patientDOB = patientDOB;
    }
    public LocalDate getPatientAdmissionDate() {
        return patientAdmissionDate;
    }
    public void setPatientAdmissionDate(LocalDate patientAdmissionDate) {
        this.patientAdmissionDate = patientAdmissionDate;
    }
    public Patient() {
    }
    
    
}
