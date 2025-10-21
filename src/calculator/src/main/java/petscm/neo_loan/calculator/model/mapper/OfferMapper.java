package petscm.neo_loan.calculator.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import petscm.neo_loan.api_schemas.server.model.CalcOffers201ResponseInner;
import petscm.neo_loan.api_schemas.server.model.DataScoring201Response;
import petscm.neo_loan.calculator.model.domain.Offer;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OfferMapper {
    CalcOffers201ResponseInner toCalcOffers201Response(Offer offer);
    List<CalcOffers201ResponseInner> toListCalcOffers201Response(List<Offer> offers);
    DataScoring201Response toDataScoring201Response(Offer offer);
}
