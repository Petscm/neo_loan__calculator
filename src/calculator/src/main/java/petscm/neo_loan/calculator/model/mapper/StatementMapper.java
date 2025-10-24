package petscm.neo_loan.calculator.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;
import petscm.neo_loan.api_schemas.server.model.CalcOffersRequest;
import petscm.neo_loan.api_schemas.server.model.DataScoringRequest;
import petscm.neo_loan.calculator.model.domain.Statement;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface StatementMapper {
    Statement toStatement(CalcOffersRequest loanStatement);
    Statement toStatement(DataScoringRequest scoringData);
}