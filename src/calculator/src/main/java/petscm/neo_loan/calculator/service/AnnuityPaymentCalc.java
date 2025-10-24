package petscm.neo_loan.calculator.service;

import org.springframework.stereotype.Service;
import petscm.neo_loan.calculator.usecase.port.MonthlyPaymentCalc;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class AnnuityPaymentCalc implements MonthlyPaymentCalc {
    @Override
    public BigDecimal calculate(int term, BigDecimal rate, BigDecimal amount) {
        BigDecimal monthlyPayment = new BigDecimal(0);
        monthlyPayment = monthlyPayment.setScale(2,  RoundingMode.HALF_UP);

        BigDecimal monthlyRate = rate
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.CEILING)
                .divide(BigDecimal.valueOf(12), 4, RoundingMode.CEILING);

        BigDecimal temp = monthlyRate.add(
                monthlyRate.divide(
                        monthlyRate.add(BigDecimal.valueOf(1))
                                .pow(term)
                                .subtract(BigDecimal.valueOf(1)),
                        RoundingMode.CEILING)
        );

        monthlyPayment = amount.multiply(temp);

        return monthlyPayment;
    }
}
