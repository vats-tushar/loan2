package com.thinkapi.loan_amortisation.service.api.loanAmortization;

import com.thinkapi.loan_amortisation.dto.accountDetails.AccountBalanceRequest;
import com.thinkapi.loan_amortisation.dto.accountDetails.AccountBalanceResponse;
import com.thinkapi.loan_amortisation.dto.loanAmortization.LoanAmortizationResponse;
import com.thinkapi.loan_amortisation.service.api.accountBalance.AccountBalanceService;
import org.springframework.stereotype.Service;

@Service
public class LoanAmortizationService {
    public LoanAmortizationResponse getAmortization(){
        AccountBalanceRequest req = new AccountBalanceRequest();
        AccountBalanceResponse accBalance = AccountBalanceService.getAccountBalance(req).block();
        System.out.println(accBalance.getAccountBalanceDetails());
    }

}
