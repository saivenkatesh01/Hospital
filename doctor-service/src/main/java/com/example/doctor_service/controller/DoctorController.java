package com.example.doctor_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.doctor_service.model.Doctor;
import com.example.doctor_service.service.DoctorService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api")
public class DoctorController {

    DoctorService doctorService;

    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    public DoctorController(DoctorService doctorservice){
        logger.info("doctor service bean created");
        this.doctorService = doctorservice;
    }

    @PostMapping("/v1/doctor")
    public ResponseEntity<Doctor>addDoctor(@RequestBody @Valid Doctor doctor){
        logger.info("entered addDoctor method"+ doctor.getDoctorName());
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    @GetMapping("/v1/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/v1/doctor")
    public ResponseEntity<Doctor> getDoctorById(@Valid @RequestParam Integer doctorId){
        return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
    }

    // @PatchMapping("/v1/doctor")
    // public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor){
    //     return ResponseEntity.ok(doctorService.updateDoctor(doctor));
    // }

    // @DeleteMapping("/v1/doctor")
    // public ResponseEntity<Doctor> deleteDoctorById(@Valid @RequestParam Integer doctorId){
    //     return ResponseEntity.ok(doctorService.deleteDoctorById(doctorId));
    // }

    




}
