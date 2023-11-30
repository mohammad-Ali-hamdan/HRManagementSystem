package com.company.hrmanagmentsystem;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


import java.util.TimeZone;

@Component
public class ApplicationStartup {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
    }
}