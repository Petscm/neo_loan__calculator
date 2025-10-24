package petscm.neo_loan.calculator.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import petscm.neo_loan.calculator.model.domain.Offer;
import petscm.neo_loan.calculator.model.domain.Statement;
import petscm.neo_loan.calculator.usecase.port.OfferSettings;

@RequiredArgsConstructor
@Component
public class CreateCredit {
    private final OfferSettings offerSettings;

    public Offer create(Statement statement) {
        var offer = new Offer();
        try {
            offerSettings
                    .setRequestedAmount(statement, offer)
                    .setTerm(statement, offer)
                    .setAmount(statement, offer)
                    .setRate(statement, offer)
                    .setMonthlyPayment(offer)
                    .setPaymentScheduleElements(offer)
                    .setPsk(offer);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
        return offer;
    }

}
