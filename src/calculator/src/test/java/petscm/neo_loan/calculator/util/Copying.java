package petscm.neo_loan.calculator.util;

import petscm.neo_loan.api_schemas.server.model.CalcOffersRequest;

public class Copying {
    public static CalcOffersRequest copy(CalcOffersRequest loanStatement){
        return new CalcOffersRequest(
                loanStatement.getAmount(),
                loanStatement.getTerm(),
                loanStatement.getFirstName(),
                loanStatement.getLastName(),
                loanStatement.getMiddleName(),
                loanStatement.getEmail(),
                loanStatement.getBirthdate(),
                loanStatement.getPassportSeries(),
                loanStatement.getPassportNumber()
        );
    }
}