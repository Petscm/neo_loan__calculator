package petscm.neo_loan.calculator.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import petscm.neo_loan.calculator.model.domain.Offer;
import petscm.neo_loan.calculator.model.domain.Statement;
import petscm.neo_loan.calculator.usecase.port.OfferSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class CreateOffers {
    private final OfferSettings offerSettings;

    public List<Offer> create(final Statement statement) {
        var offers = new ArrayList<Offer>();

        IntStream.range(0, 4).forEach(i -> {
            var offer = new Offer();
            statement.setIsInsuranceEnabled((i & 2) > 0);
            statement.setIsSalaryClient((i & 1) > 0);

            try {
                offerSettings
                        .setId(offer)
                        .setTerm(statement, offer)
                        .setRequestedAmount(statement, offer)
                        .setAmount(statement, offer)
                        .setRate(statement, offer)
                        .setMonthlyPayment(offer);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e.getCause());
            }
            offers.add(offer);
        });
        return offers;
    }
}
