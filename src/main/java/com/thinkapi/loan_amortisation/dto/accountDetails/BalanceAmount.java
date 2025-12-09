package com.thinkapi.loan_amortisation.dto.accountDetails;

import lombok.Data;

@Data
public class BalanceAmount {
    private String balanceAmountCurrency;
    private Long accountBalance;
}
