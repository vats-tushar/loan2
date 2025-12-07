package com.thinkapi.loan_amortisation.controller;

import com.thinkapi.loan_amortisation.dto.transactionHistory.TransactionHistoryResponse;
import com.thinkapi.loan_amortisation.dto.transactionHistory.TransactionHistoryRequest;
import com.thinkapi.loan_amortisation.service.api.transactionHistory.TransactionHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/transactionHistory")
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    public TransactionHistoryController(TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    @GetMapping("/{accountReference}")
    public ResponseEntity<TransactionHistoryResponse> getTransactionHistory(
            @PathVariable String accountReference,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) Integer customerId,
            @RequestParam(required = false) Double fromAmount,
            @RequestParam(required = false) Double toAmount,
            @RequestParam(required = false) Integer transactionType,
            @RequestParam(required = false) Integer transactionAccountType,
            @RequestParam(required = false) String transactionId,
            @RequestParam(required = false) String movementDescription,
            @RequestParam(required = false) String extensibilityMap,
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

        TransactionHistoryRequest req = new TransactionHistoryRequest();
        req.setAccountReference(accountReference);
        req.setFromDate(fromDate);
        req.setToDate(toDate);
        req.setCustomerId(customerId);
        req.setFromAmount(fromAmount);
        req.setToAmount(toAmount);
        req.setTransactionType(transactionType);
        req.setTransactionAccountType(transactionAccountType);
        req.setTransactionId(transactionId);
        req.setMovementDescription(movementDescription);
        req.setExtensibilityMap(extensibilityMap);
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

        return ResponseEntity.ok(transactionHistoryService.getTransactionHistory(req).block());
    }
}

