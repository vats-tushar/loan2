package com.thinkapi.loan_amortisation.service.api.accountBalance;

import com.thinkapi.loan_amortisation.dto.accountDetails.AccountBalanceRequest;
import com.thinkapi.loan_amortisation.dto.accountDetails.AccountBalanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

@Service
public class AccountBalanceService {

    private final WebClient webClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public AccountBalanceService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.tcsbancs.com/external/BaNCS/Banking/AccountManagement/v2/v1/accountManagement/account").build();
    }


    public Mono<AccountBalanceResponse> getAccountBalance(AccountBalanceRequest req) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/balanceDetails/{accountReference}")
                        .build(req.getAccountReference()))
                .header("userId", String.valueOf(req.getUserId()))
                .header("entity", req.getEntity())
                .header("languageCode", String.valueOf(req.getLanguageCode()))
                .header("ChannelType", String.valueOf(req.getChannelType()))
                .header("Co-Relationid", String.valueOf(req.getCoRelationId()))
                .header("UUIDSeqNo", String.valueOf(req.getUuidSeqNo()))
                .header("InitiatingSystem", req.getInitiatingSystem())
                .header("ServiceMode", String.valueOf(req.getServiceMode()))
                .header("Accesstoken", req.getAccessToken())
                .header("referenceId", req.getReferenceId())
                .retrieve()
                .bodyToMono(AccountBalanceResponse.class)
                .onErrorResume(ex -> {
                    System.out.println("Account API ERROR → Using fallback JSON");

                    String fallbackJson = """
                    {
                        "status": "SUCCESS",
                        "balance": 25000.50,
                        "currency": "INR",
                        "accountReference": "TEST123"
                    }
                    """;

                    try {
                        return Mono.just(
                                objectMapper.readValue(fallbackJson, AccountBalanceResponse.class)
                        );
                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("Failed to parse fallback JSON", e));
                    }
                });
    }
}