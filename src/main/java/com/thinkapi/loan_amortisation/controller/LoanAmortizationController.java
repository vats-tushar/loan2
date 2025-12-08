package com.thinkapi.loan_amortisation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class LoanAmortizationController {

    @Autowired
    private ObjectMapper objectMapper;


}
