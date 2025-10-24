package petscm.neo_loan.calculator.usecase.port;

import java.math.BigDecimal;

public interface MonthlyPaymentCalc {
    BigDecimal calculate(int term, BigDecimal rate, BigDecimal amount);
}