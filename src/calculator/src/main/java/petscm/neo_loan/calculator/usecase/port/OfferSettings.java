package petscm.neo_loan.calculator.usecase.port;

import petscm.neo_loan.calculator.model.domain.Offer;
import petscm.neo_loan.calculator.model.domain.Statement;

public interface OfferSettings {
    OfferSettings validateValues(Statement statement);
    OfferSettings setId(Offer offer);
    OfferSettings setTerm(Statement statement, Offer offer);
    OfferSettings setRequestedAmount(Statement statement, Offer offer);
    OfferSettings setAmount(Statement statement, Offer offer);
    OfferSettings setRate(Statement statement, Offer offer);
    OfferSettings setMonthlyPayment(Offer offer);
    OfferSettings setPaymentScheduleElements(Offer offer);
    OfferSettings setPsk(Offer offer);
}
