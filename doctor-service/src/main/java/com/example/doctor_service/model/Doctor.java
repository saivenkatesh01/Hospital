package com.example.doctor_service.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "doctors")
public class Doctor {

    //alt + shift + o = to import all required at once

    public Doctor() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;

    @NotBlank(message = "Doctor name is required")
    @Size(min = 2, max = 100, message = "Doctor name should be between 2 and 100 characters.")
    private String doctorName;

    @NotBlank(message = "Specialization is required")
    @Size(min = 2, max = 100, message = "Specialization should be between 2 and 100 characters.")
    private String specialization;

    @NotBlank(message = "Doctor phone number is required")
    @Pattern(
    regexp = "^[6-9]\\d{9}$",
    message = "Enter a valid 10-digit Indian mobile number"
    )
    private String doctorContact;

    @NotNull(message = "Consultation fees is required")
    @PositiveOrZero(message = "Fees cannot be negative")
    private BigDecimal consultationFee;

    @NotNull(message  = "Availability required") 
    private boolean availability;

    public Integer getDoctorId() {
        return doctorId;
    }


    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }


    public String getDoctorName() {
        return doctorName;
    }


    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public String getSpecialization() {
        return specialization;
    }


    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public String getDoctorContact() {
        return doctorContact;
    }


    public void setDoctorContact(String doctorContact) {
        this.doctorContact = doctorContact;
    }


    public BigDecimal getConsultationFee() {
        return consultationFee;
    }


    public void setConsultationFee(BigDecimal consultationFee) {
        this.consultationFee = consultationFee;
    }


    public boolean isAvailability() {
        return availability;
    }


    public void setAvailability(boolean availability) {
        this.availability = availability;
    }


    public Doctor(Integer doctorId, String doctorName, String specialization, String doctorContact,
            BigDecimal consultationFee, boolean availability) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.doctorContact = doctorContact;
        this.consultationFee = consultationFee;
        this.availability = availability;
    }



}
