// LoanRepaymentResponse.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.thinkapi.loan_amortisation.dto.loan;
import java.util.List;
import lombok.Data;

@Data
public class LoanRepaymentResponse {
    private long noOfPages;
    private LoanDetails loanDetails;
    private long totalNoOfRecords;
    private long pageSize;
    private String hasNext;
    private List<RepaymentEvent> repaymentEvents;
    private long pageNum;
}

// LoanDetails.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

@Data
class LoanDetails {
    private String loanRef;
}

// RepaymentEvent.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

@Data
class RepaymentEvent {
    private long penalPaid;
    private long installmentAmt;
    private String originalDueDt;
    private long principalPaid;
    private long projectedIntAmt;
    private String dueDt;
    private long interestDue;
    private long penalDueAmount;
    private String paidDt;
    private long principalAmt;
    private long loanId;
    private long interestPaid;
    private long expectedPrincipal;
    private long status;
}
