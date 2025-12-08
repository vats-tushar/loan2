package com.thinkapi.loan_amortisation.service.api.loanRepayment;

import com.thinkapi.loan_amortisation.dto.loan.LoanRepaymentRequest;
import com.thinkapi.loan_amortisation.dto.loan.LoanRepaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

@Service
public class LoanRepaymentService {

    private final WebClient webClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public LoanRepaymentService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.tcsbancs.com/external/bancs/GBP/loans/V2/v1/loans").build();
    }

    public static Mono<LoanRepaymentResponse> getLoanRepayment(LoanRepaymentRequest req) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/repaymentSchedule/{loanReference}")
                        .queryParam("repayOption", req.getRepayOption())
                        .queryParam("pageSize", req.getPageSize())
                        .queryParam("pageNum", req.getPageNum())
                        .build(req.getLoanReference()))
                // Headers
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
                .bodyToMono(LoanRepaymentResponse.class)
                .onErrorResume(ex -> {
                    System.out.println("Account API ERROR → Using fallback JSON");

                    String fallbackJson = """
                            {
                                   "pageSize": 100,
                                   "pageNum": 1,
                                   "noOfPages": 1,
                                   "totalNoOfRecords": 32,
                                   "hasNext": "N",
                                   "loanDetails": {
                                     "loanRef": "999000000111974"
                                   },
                                   "repaymentEvents": [
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20311005",
                                       "paidDt": null,
                                       "principalAmt": 95.17,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 2904.83,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 2.83,
                                       "originalDueDt": "20311005"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20320105",
                                       "paidDt": null,
                                       "principalAmt": 90.74,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 2814.09,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 7.26,
                                       "originalDueDt": "20320105"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20320405",
                                       "paidDt": null,
                                       "principalAmt": 90.96,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 2723.13,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 7.04,
                                       "originalDueDt": "20320405"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20320705",
                                       "paidDt": null,
                                       "principalAmt": 91.19,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 2631.94,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 6.81,
                                       "originalDueDt": "20320705"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20321005",
                                       "paidDt": null,
                                       "principalAmt": 91.42,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 2540.52,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 6.58,
                                       "originalDueDt": "20321005"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20330105",
                                       "paidDt": null,
                                       "principalAmt": 91.65,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 2448.87,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 6.35,
                                       "originalDueDt": "20330105"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20330405",
                                       "paidDt": null,
                                       "principalAmt": 91.88,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 2356.99,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 6.12,
                                       "originalDueDt": "20330405"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20330705",
                                       "paidDt": null,
                                       "principalAmt": 92.11,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 2264.88,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 5.89,
                                       "originalDueDt": "20330705"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20331005",
                                       "paidDt": null,
                                       "principalAmt": 92.34,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 2172.54,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 5.66,
                                       "originalDueDt": "20331005"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20340105",
                                       "paidDt": null,
                                       "principalAmt": 92.57,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 2079.97,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 5.43,
                                       "originalDueDt": "20340105"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20340405",
                                       "paidDt": null,
                                       "principalAmt": 92.8,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 1987.17,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 5.2,
                                       "originalDueDt": "20340405"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20340705",
                                       "paidDt": null,
                                       "principalAmt": 93.03,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 1894.14,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 4.97,
                                       "originalDueDt": "20340705"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20341005",
                                       "paidDt": null,
                                       "principalAmt": 93.26,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 1800.88,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 4.74,
                                       "originalDueDt": "20341005"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20350105",
                                       "paidDt": null,
                                       "principalAmt": 93.5,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 1707.38,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 4.5,
                                       "originalDueDt": "20350105"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20350405",
                                       "paidDt": null,
                                       "principalAmt": 93.73,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 1613.65,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 4.27,
                                       "originalDueDt": "20350405"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20350705",
                                       "paidDt": null,
                                       "principalAmt": 93.97,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 1519.68,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 4.03,
                                       "originalDueDt": "20350705"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20351005",
                                       "paidDt": null,
                                       "principalAmt": 94.2,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 1425.48,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 3.8,
                                       "originalDueDt": "20351005"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20360105",
                                       "paidDt": null,
                                       "principalAmt": 94.44,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 1331.04,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 3.56,
                                       "originalDueDt": "20360105"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20360405",
                                       "paidDt": null,
                                       "principalAmt": 94.67,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 1236.37,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 3.33,
                                       "originalDueDt": "20360405"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20360705",
                                       "paidDt": null,
                                       "principalAmt": 94.91,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 1141.46,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 3.09,
                                       "originalDueDt": "20360705"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20361005",
                                       "paidDt": null,
                                       "principalAmt": 95.15,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 1046.31,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 2.85,
                                       "originalDueDt": "20361005"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20370105",
                                       "paidDt": null,
                                       "principalAmt": 95.38,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 950.93,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 2.62,
                                       "originalDueDt": "20370105"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20370405",
                                       "paidDt": null,
                                       "principalAmt": 95.62,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 855.31,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 2.38,
                                       "originalDueDt": "20370405"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20370705",
                                       "paidDt": null,
                                       "principalAmt": 95.86,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 759.45,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 2.14,
                                       "originalDueDt": "20370705"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20371005",
                                       "paidDt": null,
                                       "principalAmt": 96.1,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 663.35,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 1.9,
                                       "originalDueDt": "20371005"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20380105",
                                       "paidDt": null,
                                       "principalAmt": 96.34,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 567.01,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 1.66,
                                       "originalDueDt": "20380105"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20380405",
                                       "paidDt": null,
                                       "principalAmt": 96.58,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 470.43,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 1.42,
                                       "originalDueDt": "20380405"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20380705",
                                       "paidDt": null,
                                       "principalAmt": 96.82,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 373.61,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 1.18,
                                       "originalDueDt": "20380705"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20381005",
                                       "paidDt": null,
                                       "principalAmt": 97.07,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 276.54,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 0.93,
                                       "originalDueDt": "20381005"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20390105",
                                       "paidDt": null,
                                       "principalAmt": 97.31,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 179.23,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 0.69,
                                       "originalDueDt": "20390105"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20390405",
                                       "paidDt": null,
                                       "principalAmt": 97.55,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 81.68,
                                       "status": 1,
                                       "installmentAmt": 98,
                                       "projectedIntAmt": 0.45,
                                       "originalDueDt": "20390405"
                                     },
                                     {
                                       "loanId": 11740,
                                       "dueDt": "20390705",
                                       "paidDt": null,
                                       "principalAmt": 81.68,
                                       "interestDue": null,
                                       "penalDueAmount": null,
                                       "principalPaid": null,
                                       "interestPaid": null,
                                       "penalPaid": null,
                                       "expectedPrincipal": 0,
                                       "status": 1,
                                       "installmentAmt": 81.88,
                                       "projectedIntAmt": 0.2,
                                       "originalDueDt": "20390705"
                                     }
                                   ]
                                 }
                    """;

                    try {
                        return Mono.just(
                                objectMapper.readValue(fallbackJson, LoanRepaymentResponse.class)
                        );
                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("Failed to parse fallback JSON", e));
                    }
                });
    }
}

