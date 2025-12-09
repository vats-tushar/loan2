package com.thinkapi.loan_amortisation.dto.accountDetails;

import lombok.Data;

@Data
public class CreditLineAmount {
    private String currency;
    private Long creditAmount;
}
