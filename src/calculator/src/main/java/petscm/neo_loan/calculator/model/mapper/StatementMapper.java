package petscm.neo_loan.calculator.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import petscm.neo_loan.api_schemas.server.model.CalcOffersRequestInner;
import petscm.neo_loan.api_schemas.server.model.DataScoringRequest;
import petscm.neo_loan.calculator.model.domain.Statement;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatementMapper {
    Statement toStatement(CalcOffersRequestInner loanStatement);
    Statement toStatement(DataScoringRequest scoringData);
}