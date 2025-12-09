package com.thinkapi.loan_amortisation.dto.loanAmortization;

import lombok.Data;

@Data
public class LoanAmortizationRequest {

    // Request params
    private String loanReference;
    private String accountReference;
    private double newSalary;
    private double balanceCushion;

}

