// TransactionHistoryResponse.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.thinkapi.loan_amortisation.dto.transactionHistory;
import java.util.List;
import lombok.Data;

@Data
public class TransactionHistoryResponse {
    private long noOfPages;
    private long totalNoOfRecords;
    private long pageSize;
    private String hasNext;
    private List<TransactionHistory> transactionHistory;
    private long pageNum;
}

// TransactionHistory.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

@Data
class TransactionHistory {
    private String entityCode;
    private String transactionDateTime;
    private long netBalance;
    private String accountCurrency;
    private String transactionRef;
    private String transactionDescription;
    private String valueDate;
    private String transactionDate;
    private String chequeNumber;
    private String extensibilityMap;
    private long transactionId;
    private long transactionType;
    private long debitCreditFlag;
    private long movementAmount;
    private String txnDscrptn4;
}
