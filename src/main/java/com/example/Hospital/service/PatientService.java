package com.example.Hospital.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.Hospital.exceptions.InsufficientFundsException;
import com.example.Hospital.exceptions.InvalidInputException;
import com.example.Hospital.exceptions.ResourceNotFoundException;
import com.example.Hospital.model.Patient;
import com.example.Hospital.repository.PatientRepository;
@Service 
public class PatientService {

    PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        // Default constructor
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        if(patient==null) throw new InvalidInputException("Patient object is null");
        patientRepository.save(patient);
        return patient;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(int patientId) {
        return patientRepository.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("Patient not found with id: "+patientId));
    }

    public ResponseEntity<Patient> updatePatient(Patient patient){
        Patient existingPatient = patientRepository.findById(patient.getPatientId()).orElseThrow(()-> new ResourceNotFoundException("Patient not found with id: "+patient.getPatientId()));
        if(patient.getPatientName()!=null){ existingPatient.setPatientName(patient.getPatientName());}
        if(patient.getPatientPhoneNumber()!=null){ existingPatient.setPatientPhoneNumber(patient.getPatientPhoneNumber());}
        if(patient.getPatientDisease()!=null){ existingPatient.setPatientDisease(patient.getPatientDisease());}
        return new ResponseEntity<Patient>(patientRepository.save(existingPatient),HttpStatus.OK);
    }

    public ResponseEntity<String> deletePatientById(int patientId){
        Patient existingPatient = patientRepository.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("Patient not found with id: "+patientId));
        patientRepository.delete(existingPatient);
        return new ResponseEntity<String>(existingPatient.getPatientName()+" Patient deleted successfully",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAllPatients(){
        patientRepository.deleteAll();
        return new ResponseEntity<String>("All patients deleted successfully",HttpStatus.OK);
    }

    @Transactional 
    public ResponseEntity<String> depositBalance(int patientId, Long amount) {
        Patient existingPatient = patientRepository.findByIdForUpdate( patientId).orElseThrow(()-> new ResourceNotFoundException("Patient not found with id: "+patientId));
        existingPatient.setPatientBalance(existingPatient.getPatientBalance() + amount);
        return new ResponseEntity<>(amount+" added to Patient "+ existingPatient.getPatientName()+" balance. updated balance is "+existingPatient.getPatientBalance(), HttpStatus.OK);
    }

    @Transactional 
    public ResponseEntity<String> debitBalance(int patientId, Long amount) {
        Patient existingPatient = patientRepository.findByIdForUpdate(patientId).orElseThrow(()-> new ResourceNotFoundException("Patient not found with id: "+patientId));
        if(existingPatient.getPatientBalance()<amount) throw new InsufficientFundsException("Insufficient Funds. Debit amount "+ amount +" is greater than balance available "+ existingPatient.getPatientBalance());
        existingPatient.setPatientBalance(existingPatient.getPatientBalance() - amount);
        return new ResponseEntity<>(amount+" debited from Patient "+ existingPatient.getPatientName()+" balance. updated balance is "+existingPatient.getPatientBalance(), HttpStatus.OK);
    
    }

}
