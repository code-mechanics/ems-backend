package com.ems.backend.controller;

import com.ems.backend.annotation.RequiredHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EchoController {

    @GetMapping("/echo")
    public String echo() {
        return "Hi. ems-backend is up and running!!!";
    }

    @GetMapping("/echoHeaders")
    @RequiredHeaders({
            "countryCode",
            "sub"
    })
    public String echo(@RequestHeader("countryCode") String countryCode,
                       @RequestHeader("sub") String sub,
                       @RequestHeader("acr") String acr) {
        return "Hi. ems-backend is up and running!!!";
    }

//    @RequestHeader(COUNTRY_CODE) String countryCode
}
