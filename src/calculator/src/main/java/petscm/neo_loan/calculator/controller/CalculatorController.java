package petscm.neo_loan.calculator.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import petscm.neo_loan.api_schemas.server.api.OpenApiCalculatorOffersApi;
import petscm.neo_loan.api_schemas.server.model.CalcOffers201ResponseInner;
import petscm.neo_loan.api_schemas.server.model.CalcOffersRequestInner;
import petscm.neo_loan.api_schemas.server.model.DataScoring201Response;
import petscm.neo_loan.api_schemas.server.model.DataScoringRequest;

import java.util.List;
import java.util.Optional;

@RestController
public class CalculatorController implements OpenApiCalculatorOffersApi {
    @Override
    public ResponseEntity<DataScoring201Response> dataScoring(Optional<DataScoringRequest> dataScoringRequest) {
        return OpenApiCalculatorOffersApi.super.dataScoring(dataScoringRequest);
    }

    @Override
    public ResponseEntity<List<CalcOffers201ResponseInner>> calcOffers(Optional<List<@Valid CalcOffersRequestInner>> calcOffersRequestInner) {
        return OpenApiCalculatorOffersApi.super.calcOffers(calcOffersRequestInner);
    }
}
