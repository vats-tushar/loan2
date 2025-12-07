package com.thinkapi.loan_amortisation.dto.transactionHistory;

import lombok.Data;

@Data
public class TransactionHistoryRequest {

    // Path variable
    private String accountReference;

    // Query params
    private String fromDate;
    private Integer customerId;
    private String toDate;
    private Double fromAmount;
    private Double toAmount;
    private Integer transactionType;
    private Integer transactionAccountType;
    private String transactionId;
    private String movementDescription;
    private String extensibilityMap;
    private Integer pageSize = 100;   // default
    private Integer pageNum = 1;     // default

    // Headers
    private Integer userId;
    private String entity;
    private Integer languageCode;
    private Integer channelType;
    private Integer coRelationId;
    private Integer uuidSeqNo;
    private String initiatingSystem;
    private Integer serviceMode;
    private String accessToken;
    private String referenceId;
}

