package com.thinkapi.loan_amortisation.dto.loan;

import lombok.Data;

@Data
public class LoanRepaymentRequest {

    // Path variable
    private String loanReference;

    // Query params
    private Integer repayOption;   // 1-default, 2-future
    private Integer pageSize = 22; // default
    private Integer pageNum = 1;   // default

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

