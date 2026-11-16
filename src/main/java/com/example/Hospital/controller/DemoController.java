package com.example.Hospital.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class DemoController {
    
    @GetMapping("/hello")
    public String hello(){
        return "Hello welcome to Hospital Management System";
        
    }
}

