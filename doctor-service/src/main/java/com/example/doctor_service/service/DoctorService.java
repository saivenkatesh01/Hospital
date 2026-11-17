package com.example.doctor_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.doctor_service.model.Doctor;
import com.example.doctor_service.repository.DoctorRepository;


@Service 
public class DoctorService {

    DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    public Doctor addDoctor(Doctor doctor) {
        // if (doctor == null) throw InvalidInputException("Doctor object is null");
        doctorRepository.save(doctor);
        return doctor;
    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Integer doctorId){
        return doctorRepository.findById(doctorId).orElse(null);
    }

    // public Doctor updateDoctor(Doctor doctor){
    //     return doctorRepository.update
    // }

    // public Doctor deleteDoctorById(Integer doctorId){
    //     return doctorRepository.deleteById(doctorId).orElse(null);
    // }

}