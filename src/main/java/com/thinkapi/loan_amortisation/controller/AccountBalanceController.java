package com.thinkapi.loan_amortisation.controller;

import com.thinkapi.loan_amortisation.dto.accountDetails.AccountBalanceRequest;
import com.thinkapi.loan_amortisation.dto.accountDetails.AccountBalanceResponse;
import com.thinkapi.loan_amortisation.service.api.accountBalance.AccountBalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/balance")
public class AccountBalanceController {

    private final AccountBalanceService accountApiService;

    public AccountBalanceController(AccountBalanceService accountBalanceService) {
        this.accountApiService = accountBalanceService;
    }

    @GetMapping("/{accountReference}")
    public ResponseEntity<AccountBalanceResponse> getAccountBalance(
            @PathVariable String accountReference,
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

        AccountBalanceRequest req = new AccountBalanceRequest();
        req.setAccountReference(accountReference);
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

        return ResponseEntity.ok(accountApiService.getAccountBalance(req).block());
    }
}

