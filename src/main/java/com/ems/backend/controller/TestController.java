package com.ems.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess(@AuthenticationPrincipal UserDetails userDetails) {
//        UserDetails userDetails =
//                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("userDetails:user:"+userDetails);
        System.out.println("userDetails:password:"+userDetails.getPassword());
        System.out.println("userDetails:username:"+userDetails.getUsername());
        System.out.println("userDetails:authorities:"+userDetails.getAuthorities());
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("userDetails:moderator:"+userDetails);
        System.out.println("userDetails:password:"+userDetails.getPassword());
        System.out.println("userDetails:username:"+userDetails.getUsername());
        System.out.println("userDetails:authorities:"+userDetails.getAuthorities());
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("userDetails:admin:"+userDetails);
        System.out.println("userDetails:password:"+userDetails.getPassword());
        System.out.println("userDetails:username:"+userDetails.getUsername());
        System.out.println("userDetails:authorities:"+userDetails.getAuthorities());
        return "Admin Board.";
    }

    @GetMapping("/instrutor")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public String instructorAccess(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("userDetails:instructor:"+userDetails);
        System.out.println("userDetails:password:"+userDetails.getPassword());
        System.out.println("userDetails:username:"+userDetails.getUsername());
        System.out.println("userDetails:authorities:"+userDetails.getAuthorities());
        return "Instructor Board.";
    }

    @GetMapping("/groupAdmin")
    @PreAuthorize("hasRole('GROUP_ADMIN')")
    public String groupAdminAccess(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("userDetails:group admin:"+userDetails);
        System.out.println("userDetails:password:"+userDetails.getPassword());
        System.out.println("userDetails:username:"+userDetails.getUsername());
        System.out.println("userDetails:authorities:"+userDetails.getAuthorities());
        return "GROUP_ADMIN Board.";
    }
}
