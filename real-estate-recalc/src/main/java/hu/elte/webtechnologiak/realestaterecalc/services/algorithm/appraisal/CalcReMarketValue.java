package hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.Appraisal;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.Algorithm;

import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;

public class CalcReMarketValue implements Algorithm {

	@Override
	public void execute( final BaseEntity entity ) {
		if (entity instanceof RealEstate) {
			final RealEstate realEstate = (RealEstate) entity;
			final Optional<Appraisal> maybeAppraisal = realEstate.getAppraisals().stream()
				                                           .max(comparing(Appraisal::getAppraisalDate, nullsFirst(naturalOrder())));
			if (maybeAppraisal.isPresent()) {
				final Appraisal appraisal = maybeAppraisal.get();
				realEstate.setMarketValueOccy(appraisal.getTotalAppraisedValue());
				realEstate.setMarketValueCcy(appraisal.getTotalAppraisedValueCcy());
			}
		}
	}

}
