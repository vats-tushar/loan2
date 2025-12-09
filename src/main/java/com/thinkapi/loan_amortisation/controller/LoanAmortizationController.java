package com.thinkapi.loan_amortisation.controller;

import com.thinkapi.loan_amortisation.dto.loanAmortization.LoanAmortizationRequest;
import com.thinkapi.loan_amortisation.dto.loanAmortization.LoanAmortizationResponse;
import com.thinkapi.loan_amortisation.service.api.loanAmortization.LoanAmortizationService;
import com.thinkapi.loan_amortisation.service.api.loanRepayment.LoanRepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/loan")
public class LoanAmortizationController {

    private final LoanAmortizationService loanAmortizationService;

    public LoanAmortizationController(LoanAmortizationService loanAmortizationService) {
        this.loanAmortizationService = loanAmortizationService;
    }

    @GetMapping("/loanAmortization")
    public ResponseEntity<LoanAmortizationResponse> getAmortizationEmi(
            @RequestParam String loanReference,
            @RequestParam String accountReference,
            @RequestParam double newSalary,
            @RequestParam double balanceCushion
            ){
        LoanAmortizationRequest req = new LoanAmortizationRequest();
        req.setLoanReference(loanReference);
        req.setAccountReference(accountReference);
        req.setNewSalary(newSalary);
        req.setBalanceCushion(balanceCushion);

        return ResponseEntity.ok(loanAmortizationService.getAmortization(req));
    }


}
