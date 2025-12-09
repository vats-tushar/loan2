package com.thinkapi.loan_amortisation.service.api.loanAmortization;

import com.thinkapi.loan_amortisation.dto.accountDetails.AccountBalanceRequest;
import com.thinkapi.loan_amortisation.dto.accountDetails.AccountBalanceResponse;
import com.thinkapi.loan_amortisation.dto.loan.LoanRepaymentRequest;
import com.thinkapi.loan_amortisation.dto.loan.LoanRepaymentResponse;
import com.thinkapi.loan_amortisation.dto.loan.RepaymentEvent;
import com.thinkapi.loan_amortisation.dto.loanAmortization.LoanAmortizationRequest;
import com.thinkapi.loan_amortisation.dto.loanAmortization.LoanAmortizationResponse;
import com.thinkapi.loan_amortisation.dto.transactionHistory.TransactionHistory;
import com.thinkapi.loan_amortisation.dto.transactionHistory.TransactionHistoryRequest;
import com.thinkapi.loan_amortisation.dto.transactionHistory.TransactionHistoryResponse;
import com.thinkapi.loan_amortisation.service.api.accountBalance.AccountBalanceService;
import com.thinkapi.loan_amortisation.service.api.loanRepayment.LoanRepaymentService;
import com.thinkapi.loan_amortisation.service.api.transactionHistory.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LoanAmortizationService {
    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @Autowired
    private LoanRepaymentService loanRepaymentService;

    public LoanAmortizationResponse getAmortization(LoanAmortizationRequest req){
        AccountBalanceRequest accReq = new AccountBalanceRequest();
        accReq.setAccountReference(req.getAccountReference());
        AccountBalanceResponse accBalance = accountBalanceService.getAccountBalance(accReq).block();
        System.out.println("Account Balance : " + accBalance.getAccountBalanceDetails().getBalance().getAmount().getAccountBalance());

        TransactionHistoryRequest trhReq = new TransactionHistoryRequest();
        trhReq.setAccountReference(req.getAccountReference());
        TransactionHistoryResponse tranHistory = transactionHistoryService.getTransactionHistory(trhReq).block();
        List<TransactionHistory> listHist = tranHistory.getTransactionHistory();

        LoanRepaymentRequest repayReq = new LoanRepaymentRequest();
        repayReq.setLoanReference(req.getLoanReference());
        LoanRepaymentResponse repaySchedule = loanRepaymentService.getLoanRepayment(repayReq).block();
        System.out.println("Repay Schedule : " + repaySchedule.getRepaymentEvents());

        List<RepaymentEvent> eventList = repaySchedule.getRepaymentEvents();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate d1 = LocalDate.parse(eventList.get(0).getDueDt(), f);
        LocalDate d2 = LocalDate.parse(eventList.get(1).getDueDt(), f);

        long days = ChronoUnit.DAYS.between(d1,d2);
        System.out.println(days + " " + Math.round(days/30.0));

        double suggestedInstallment = suggestEmiIncrease(req.getNewSalary(),
                accBalance.getAccountBalanceDetails().getBalance().getAmount().getAccountBalance(),
                listHist,
                req.getBalanceCushion(),
                Math.round(days/30.0)
        );

        System.out.println("Suggested EMI : " + suggestedInstallment);



        LoanAmortizationResponse tempL = getAmortizedEmi(suggestedInstallment, eventList);


        return tempL;
    }

    public double suggestEmiIncrease(double newSalary, double acBalance, List<TransactionHistory> listHist, double balanceCushion, long tenure){
        double debitSum=0;
        double creditSum=0;
        for(int i=listHist.size()-1; i>0; i--){
            if(i<listHist.size()-30*tenure) break;
            if(listHist.get(i).getDebitCreditFlag() == 1) creditSum+=listHist.get(i).getMovementAmount();
            else if (listHist.get(i).getDebitCreditFlag() == 2)  debitSum+=listHist.get(i).getMovementAmount();

        }
        System.out.println("Inside suggestedEMi : " + tenure*newSalary + " "+ acBalance + " " + creditSum + " " + debitSum + " " + balanceCushion);
        double tempSugg = tenure*newSalary + acBalance + creditSum - debitSum -  balanceCushion;
        return tempSugg;
    }

    public LoanAmortizationResponse getAmortizedEmi(double suggestedEmi, List<RepaymentEvent> eventList){
        for(int i=0; i<eventList.size();i++){
            System.out.println(eventList.get(i).getProjectedIntAmt() + " " + eventList.get(i).getPrincipalAmt());
        }

        return new LoanAmortizationResponse();
    }

}
