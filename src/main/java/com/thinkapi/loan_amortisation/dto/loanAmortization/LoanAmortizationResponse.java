
package com.thinkapi.loan_amortisation.dto.loanAmortization;
import lombok.Data;

@Data
public class LoanAmortizationResponse {
    private Long newAmortizedValue; //loan
    private double tenureMonths; //loan
    private Long suggestedEmi; //acc
    private Long monthlyExpenses; //tranApi
    private Long savingsPercent; //loan
    private Long existingEmi; //loanAPI
    private Long outstandingPrincipal; //loanAPI
    private Long monthlyIncome; //userInput
    private Long monthlySavings; //tranAPI
}