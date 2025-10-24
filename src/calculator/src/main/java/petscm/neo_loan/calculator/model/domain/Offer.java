package petscm.neo_loan.calculator.model.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import petscm.neo_loan.calculator.usecase.port.MonthlyPaymentCalc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    private UUID statementId;
    private BigDecimal requestedAmount;
    private BigDecimal totalAmount;
    private Integer term;
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private Boolean isInsuranceEnabled;
    private Boolean isSalaryClient;
    private BigDecimal psk;
    private List<PaymentScheduleElement> paymentSchedule;

    @RequiredArgsConstructor
    final class Builder {
        private final MonthlyPaymentCalc monthlyPaymentCalculator;

        @Value("${offers-calculator.base-rate}")
        private BigDecimal baseRate;

        @Value("${offers-calculator.base-insurance-rate}")
        private volatile BigDecimal insuranceBaseRate;

        Offer offer;
        {
            offer = new Offer();
        }

        public Builder validate(Statement statement) {
            return this;
        }


    }
}