package hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal;

import hu.elte.webtechnologiak.realestaterecalc.config.SpringContext;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.Appraisal;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.Algorithm;
import hu.elte.webtechnologiak.realestaterecalc.services.utils.CurrencyUtil;

import java.io.IOException;

public class CalcAppraisalCurrencies implements Algorithm {

	private final CurrencyUtil currencyUtil = SpringContext.getBean(CurrencyUtil.class);

	@Override
	public void execute( final BaseEntity entity ) throws IOException {
		if (entity instanceof Appraisal) {
			final Appraisal appraisal = (Appraisal) entity;
			if (appraisal.getAppraisedMarketValueOfBuildingsOccy() != null && appraisal.getAppraisedMarketValueOfBuildingsCcy() != null) {
				final Double appraisedMarketValueOfBuildingsHuf = currencyUtil.convert(appraisal.getAppraisedMarketValueOfBuildingsCcy(), "HUF", appraisal.getAppraisedMarketValueOfBuildingsOccy());
				appraisal.setAppraisedMarketValueOfBuildingsHuf(appraisedMarketValueOfBuildingsHuf);
				final Double appraisedMarketValueOfBuildingsUsd = currencyUtil.convert(appraisal.getAppraisedMarketValueOfBuildingsCcy(), "USD", appraisal.getAppraisedMarketValueOfBuildingsOccy());
				appraisal.setAppraisedMarketValueOfBuildingsUsd(appraisedMarketValueOfBuildingsUsd);
				final Double appraisedMarketValueOfBuildingsEur = currencyUtil.convert(appraisal.getAppraisedMarketValueOfBuildingsCcy(), "EUR", appraisal.getAppraisedMarketValueOfBuildingsOccy());
				appraisal.setAppraisedMarketValueOfBuildingsEur(appraisedMarketValueOfBuildingsEur);
			}
			if (appraisal.getAppraisedMarketValueOfLandOccy() != null && appraisal.getAppraisedMarketValueOfLandCcy() != null) {
				final Double appraisedMarketValueOfLandHuf = currencyUtil.convert(appraisal.getAppraisedMarketValueOfLandCcy(), "HUF", appraisal.getAppraisedMarketValueOfLandOccy());
				appraisal.setAppraisedMarketValueOfLandHuf(appraisedMarketValueOfLandHuf);
				final Double appraisedMarketValueOfLandUsd = currencyUtil.convert(appraisal.getAppraisedMarketValueOfLandCcy(), "USD", appraisal.getAppraisedMarketValueOfLandOccy());
				appraisal.setAppraisedMarketValueOfLandUsd(appraisedMarketValueOfLandUsd);
				final Double appraisedMarketValueOfLandEur = currencyUtil.convert(appraisal.getAppraisedMarketValueOfLandCcy(), "EUR", appraisal.getAppraisedMarketValueOfLandOccy());
				appraisal.setAppraisedMarketValueOfLandEur(appraisedMarketValueOfLandEur);
			}
			if (appraisal.getTotalAppraisedValue() != null && appraisal.getTotalAppraisedValueCcy() != null) {
				final Double totalAppraisedValueHuf = currencyUtil.convert(appraisal.getTotalAppraisedValueCcy(), "HUF", appraisal.getTotalAppraisedValue());
				appraisal.setTotalAppraisedValueHuf(totalAppraisedValueHuf);
				final Double totalAppraisedValueUsd = currencyUtil.convert(appraisal.getTotalAppraisedValueCcy(), "USD", appraisal.getTotalAppraisedValue());
				appraisal.setTotalAppraisedValueUsd(totalAppraisedValueUsd);
				final Double totalAppraisedValueEur = currencyUtil.convert(appraisal.getTotalAppraisedValueCcy(), "EUR", appraisal.getTotalAppraisedValue());
				appraisal.setTotalAppraisedValueEur(totalAppraisedValueEur);
			}


		}
	}

}
