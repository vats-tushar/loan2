package com.thinkapi.loan_amortisation.controller;

import com.thinkapi.loan_amortisation.dto.loan.LoanRepaymentRequest;
import com.thinkapi.loan_amortisation.dto.loan.LoanRepaymentResponse;
import com.thinkapi.loan_amortisation.service.api.loanRepayment.LoanRepaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan/repaymentSchedule")
public class LoanRepaymentController {

    private final LoanRepaymentService loanRepaymentService;

    public LoanRepaymentController(LoanRepaymentService loanRepaymentService) {
        this.loanRepaymentService = loanRepaymentService;
    }

    @GetMapping("/{loanReference}")
    public ResponseEntity<LoanRepaymentResponse> getRepaymentSchedule(
            @PathVariable String loanReference,
            @RequestParam Integer repayOption,
            @RequestParam(defaultValue = "22") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum,

            @RequestHeader Integer userId,
            @RequestHeader String entity,
            @RequestHeader Integer languageCode,
            @RequestHeader(required = false) Integer channelType,
            @RequestHeader(required = false, name = "Co-Relationid") Integer coRelationId,
            @RequestHeader(required = false, name = "UUIDSeqNo") Integer uuidSeqNo,
            @RequestHeader(required = false) String initiatingSystem,
            @RequestHeader(required = false) Integer serviceMode,
            @RequestHeader(required = false, name = "Accesstoken") String accessToken,
            @RequestHeader(required = false) String referenceId
    ) {

        LoanRepaymentRequest req = new LoanRepaymentRequest();
        req.setLoanReference(loanReference);
        req.setRepayOption(repayOption);
        req.setPageSize(pageSize);
        req.setPageNum(pageNum);

        req.setUserId(userId);
        req.setEntity(entity);
        req.setLanguageCode(languageCode);
        req.setChannelType(channelType);
        req.setCoRelationId(coRelationId);
        req.setUuidSeqNo(uuidSeqNo);
        req.setInitiatingSystem(initiatingSystem);
        req.setServiceMode(serviceMode);
        req.setAccessToken(accessToken);
        req.setReferenceId(referenceId);

        return ResponseEntity.ok(loanRepaymentService.getLoanRepayment(req).block());
    }
}

