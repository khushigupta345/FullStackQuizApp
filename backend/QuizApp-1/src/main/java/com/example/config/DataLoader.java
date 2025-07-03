package com.example.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.entity.User;
import com.example.enums.UserRole;
import com.example.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;


    @Override
    public void run(String... args) {
        String adminEmail = "admin@gmail.com";

        if (userRepo.findByEmail(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setName("Admin");
            admin.setPassword("admin"); 
            admin.setRole(UserRole.ADMIN);

            userRepo.save(admin);
            System.out.println(" Admin user created: " + adminEmail);
        } else {
            System.out.println("Admin user already exists");
        }
    }
}
