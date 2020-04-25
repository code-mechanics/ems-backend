package com.ems.backend.Entities;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @GetMapping("/echo")
    public String echo() {
        return "Hi. ems-backend is up and running!!!";
    }
}
