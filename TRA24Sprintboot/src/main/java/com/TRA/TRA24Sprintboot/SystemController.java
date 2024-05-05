package com.TRA.TRA24Sprintboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {
    @GetMapping("system")
    public String handleRequest() {
        return "Invoicing System";
    }
}
