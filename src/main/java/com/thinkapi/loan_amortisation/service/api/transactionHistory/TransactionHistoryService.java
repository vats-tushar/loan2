package com.thinkapi.loan_amortisation.service.api.transactionHistory;

import com.thinkapi.loan_amortisation.dto.transactionHistory.TransactionHistoryRequest;
import com.thinkapi.loan_amortisation.dto.transactionHistory.TransactionHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

@Service
public class TransactionHistoryService {

    private final WebClient webClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public TransactionHistoryService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.tcsbancs.com/external/BaNCS/Banking/AccountManagement/v2/v1/accountManagement/account").build();
    }

    public Mono<TransactionHistoryResponse> getTransactionHistory(TransactionHistoryRequest req) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/transactionHistory/{accountReference}")
                        .queryParam("fromDate", req.getFromDate())
                        .queryParam("customerId", req.getCustomerId())
                        .queryParam("toDate", req.getToDate())
                        .queryParam("fromAmount", req.getFromAmount())
                        .queryParam("toAmount", req.getToAmount())
                        .queryParam("transactionType", req.getTransactionType())
                        .queryParam("transactionAccountType", req.getTransactionAccountType())
                        .queryParam("transactionId", req.getTransactionId())
                        .queryParam("movementDescription", req.getMovementDescription())
                        .queryParam("extensibilityMap", req.getExtensibilityMap())
                        .queryParam("pageSize", req.getPageSize())
                        .queryParam("pageNum", req.getPageNum())
                        .build(req.getAccountReference()))
                // headers
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
                .bodyToMono(TransactionHistoryResponse.class)
                .onErrorResume(ex -> {
                    System.out.println("Account API ERROR → Using fallback JSON");

                    String fallbackJson = """
                            {
                              "pageSize": 22,
                              "pageNum": 1,
                              "noOfPages": 1,
                              "totalNoOfRecords": 2,
                              "hasNext": "N",
                              "transactionHistory": [
                                {
                                  "accountCurrency": "USD",
                                  "debitCreditFlag": 2,
                                  "movementAmount": 15,
                                  "netBalance": 9986,
                                  "transactionDescription": "272-10128:Loan Rollover Fee Disbursement Of 101000000105302",
                                  "transactionDate": "20290830",
                                  "transactionId": 10128,
                                  "transactionType": 272,
                                  "valueDate": "20290830",
                                  "transactionRef": "272-10128",
                                  "entityCode": "GPRDTTSTOU",
                                  "chequeNumber": 0,
                                  "transactionDateTime": "20230403122519",
                                  "extensibilityMap": null
                                },
                                {
                                  "accountCurrency": "USD",
                                  "debitCreditFlag": 1,
                                  "movementAmount": 10001,
                                  "netBalance": 10001,
                                  "transactionDescription": "272-10128:Loan disbursement 101000000105302",
                                  "transactionDate": "20290830",
                                  "transactionId": 10128,
                                  "transactionType": 272,
                                  "valueDate": "20290830",
                                  "transactionRef": "272-10128",
                                  "entityCode": "GPRDTTSTOU",
                                  "chequeNumber": 0,
                                  "transactionDateTime": "20230403122519",
                                  "extensibilityMap": null
                                }
                              ]
                            }
                    """;

                    try {
                        return Mono.just(
                                objectMapper.readValue(fallbackJson, TransactionHistoryResponse.class)
                        );
                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("Failed to parse fallback JSON", e));
                    }
                });
    }
}

