package com.thinkapi.loan_amortisation.service.api.loanAmortization;

import com.thinkapi.loan_amortisation.dto.accountDetails.AccountBalanceRequest;
import com.thinkapi.loan_amortisation.dto.accountDetails.AccountBalanceResponse;
import com.thinkapi.loan_amortisation.dto.loan.LoanRepaymentRequest;
import com.thinkapi.loan_amortisation.dto.loan.LoanRepaymentResponse;
import com.thinkapi.loan_amortisation.dto.loanAmortization.LoanAmortizationResponse;
import com.thinkapi.loan_amortisation.dto.transactionHistory.TransactionHistoryRequest;
import com.thinkapi.loan_amortisation.dto.transactionHistory.TransactionHistoryResponse;
import com.thinkapi.loan_amortisation.service.api.accountBalance.AccountBalanceService;
import com.thinkapi.loan_amortisation.service.api.loanRepayment.LoanRepaymentService;
import com.thinkapi.loan_amortisation.service.api.transactionHistory.TransactionHistoryService;
import org.springframework.stereotype.Service;

@Service
public class LoanAmortizationService {
    public LoanAmortizationResponse getAmortization(){
        AccountBalanceRequest accReq = new AccountBalanceRequest();
        AccountBalanceResponse accBalance = AccountBalanceService.getAccountBalance(accReq).block();
        System.out.println(accBalance.getAccountBalanceDetails());

        TransactionHistoryRequest trhReq = new TransactionHistoryRequest();
        TransactionHistoryResponse tranHistory = TransactionHistoryService.getTransactionHistory(trhReq).block();
        System.out.println(tranHistory.getTransactionHistory());

        LoanRepaymentRequest repayReq = new LoanRepaymentRequest();
        LoanRepaymentResponse repaySchedule = LoanRepaymentService.getLoanRepayment(repayReq).block();
        System.out.println(repaySchedule.getLoanDetails());
    }



}
