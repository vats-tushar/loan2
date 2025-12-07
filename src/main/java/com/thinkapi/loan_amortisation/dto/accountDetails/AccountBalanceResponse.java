// AccountBalanceResponse.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.thinkapi.loan_amortisation.dto.accountDetails;
import lombok.Data;

@Data
public class AccountBalanceResponse {
    private long noOfPages;
    private AccountBalanceDetails accountBalanceDetails;
    private long totalNoOfRecords;
    private long pageSize;
    private String hasNext;
    private long pageNum;
}

// AccountBalanceDetails.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

@Data
class AccountBalanceDetails {
    private Balance balance;
    private String extensibilityMap;
}

// Balance.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

@Data
class Balance {
    private String dateTime;
    private BalanceAmount amount;
    private String accountReference;
    private CreditLine creditLine;
    private String type;
    private String creditDebitIndicator;
}

// BalanceAmount.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

@Data
class BalanceAmount {
    private String balanceAmountCurrency;
    private long accountBalance;
}

// CreditLine.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

@Data
class CreditLine {
    private CreditLineAmount amount;
    private String balanceType;
    private String included;
}

// CreditLineAmount.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

@Data
class CreditLineAmount {
    private String currency;
    private long creditAmount;
}
