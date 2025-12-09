package com.thinkapi.loan_amortisation.dto.loan;

import lombok.Data;

@Data
public class RepaymentEvent {
    private Long penalPaid;
    private Double installmentAmt;
    private String originalDueDt;
    private Double principalPaid;
    private Double projectedIntAmt;
    private String dueDt;
    private Long interestDue;
    private Long penalDueAmount;
    private String paidDt;
    private Double principalAmt;
    private Long loanId;
    private Double interestPaid;
    private Double expectedPrincipal;
    private Long status;
}
