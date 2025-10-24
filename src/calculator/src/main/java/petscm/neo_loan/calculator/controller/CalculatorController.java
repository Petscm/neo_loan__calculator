package petscm.neo_loan.calculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import petscm.neo_loan.api_schemas.server.api.OpenApiCalculatorOffersApi;
import petscm.neo_loan.api_schemas.server.model.CalcOffers201ResponseInner;
import petscm.neo_loan.api_schemas.server.model.CalcOffersRequest;
import petscm.neo_loan.api_schemas.server.model.DataScoring201Response;
import petscm.neo_loan.api_schemas.server.model.DataScoringRequest;
import petscm.neo_loan.calculator.model.mapper.OfferMapper;
import petscm.neo_loan.calculator.model.mapper.StatementMapper;
import petscm.neo_loan.calculator.usecase.CreateCredit;
import petscm.neo_loan.calculator.usecase.CreateOffers;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalculatorController implements OpenApiCalculatorOffersApi {
    private final CreateCredit createCredit;
    private final CreateOffers createOffers;

    private final OfferMapper offerMapper;
    private final StatementMapper statementMapper;

    @Override
    public ResponseEntity<DataScoring201Response> dataScoring(DataScoringRequest dataScoringRequest) {
        var statement = statementMapper.toStatement(dataScoringRequest);
        var credit = offerMapper.toDataScoring201Response(createCredit.create(statement));

        return ResponseEntity.created(URI.create("")).body(credit);
    }

    @Override
    public ResponseEntity<List<CalcOffers201ResponseInner>> calcOffers(CalcOffersRequest calcOffersRequest) {
        var statement = statementMapper.toStatement(calcOffersRequest);
        var offers = offerMapper.toListCalcOffers201Response(createOffers.create(statement));

        return ResponseEntity.created(URI.create("")).body(offers);
    }
}
