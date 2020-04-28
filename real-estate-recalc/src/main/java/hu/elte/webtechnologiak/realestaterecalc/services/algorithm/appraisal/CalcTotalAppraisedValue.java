package hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.Appraisal;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.utils.NumberUtil;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.Algorithm;

public class CalcTotalAppraisedValue implements Algorithm {

	@Override
	public void execute( final BaseEntity entity ) {
		if (entity instanceof Appraisal) {
			final Appraisal appraisal = (Appraisal) entity;
			appraisal.setTotalAppraisedValue(NumberUtil.zero(appraisal.getAppraisedMarketValueOfBuildingsOccy()) + NumberUtil.zero(appraisal.getAppraisedMarketValueOfLandOccy()));
			appraisal.setTotalAppraisedValueCcy(appraisal.getAppraisedMarketValueOfBuildingsCcy() != null ? appraisal.getAppraisedMarketValueOfBuildingsCcy() : appraisal.getAppraisedMarketValueOfLandCcy());
		}
	}

}
