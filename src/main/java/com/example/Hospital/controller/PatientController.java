package com.example.Hospital.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Hospital.model.Patient;
import com.example.Hospital.service.PatientService;

import jakarta.validation.Valid;

@RestController 
public class PatientController {

    private PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patient")
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient){
        return ResponseEntity.ok(patientService.addPatient(patient));
    }
    @GetMapping ("/patients")
    public ResponseEntity<?> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/patient")
    public ResponseEntity<?> getPatientById(@RequestParam int patientId) {
        // Logic to retrieve a patient by ID
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    @PatchMapping("/patient")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        return patientService.updatePatient(patient);
    }

    @DeleteMapping("/patient")
    public ResponseEntity<String> deletePatientById(@RequestParam int patientId){
        return patientService.deletePatientById(patientId);
    }

    @DeleteMapping("/patients")
    public ResponseEntity<String> deleteAllPatients(){
        return patientService.deleteAllPatients();
    }

    @PatchMapping("/deposit")
    public ResponseEntity<String> depositBalance(@RequestParam int patientId, @RequestParam Long amount){
        return patientService.depositBalance(patientId, amount);
    }

    @PatchMapping ("/debit")
    public ResponseEntity<String> debitBalance(@RequestParam int patientId, @RequestParam Long amount){
        return patientService.debitBalance(patientId, amount);
    }
}
