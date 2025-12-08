
package com.thinkapi.loan_amortisation.dto.loanAmortization;
import lombok.Data;

@Data
public class LoanAmortizationResponse {
    private long newAmortizedValue;
    private double tenureMonths;
    private long suggestedEmi;
    private long monthlyExpenses;
    private long savingsPercent;
    private long existingEmi;
    private long outstandingPrincipal;
    private long monthlyIncome;
    private long monthlySavings;
}