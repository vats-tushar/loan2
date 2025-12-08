
package com.thinkapi.loan_amortisation.dto.loanAmortization;
import lombok.Data;

@Data
public class LoanAmortizationResponse {
    private long newAmortizedValue; //loan
    private double tenureMonths; //loan
    private long suggestedEmi; //acc
    private long monthlyExpenses; //tranApi
    private long savingsPercent; //loan
    private long existingEmi; //loanAPI
    private long outstandingPrincipal; //loanAPI
    private long monthlyIncome; //userInput
    private long monthlySavings; //tranAPI
}