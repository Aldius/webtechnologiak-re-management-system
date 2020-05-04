package hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal;

import hu.elte.webtechnologiak.realestaterecalc.config.SpringContext;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.Algorithm;
import hu.elte.webtechnologiak.realestaterecalc.services.utils.CurrencyUtil;

import java.io.IOException;

public class CalcWeatherDiscValueCurrencies implements Algorithm {

	private final CurrencyUtil currencyUtil = SpringContext.getBean(CurrencyUtil.class);

	@Override
	public void execute( final BaseEntity entity ) throws IOException {
		if (entity instanceof RealEstate) {
			final RealEstate realEstate = (RealEstate) entity;
			if (realEstate.getWeatherDiscountedValueOccy() != null && realEstate.getWeatherDiscountedValueCcy() != null) {
				final Double weatherDiscountedValueInHuf = currencyUtil.convert(realEstate.getWeatherDiscountedValueCcy(), "HUF", realEstate.getWeatherDiscountedValueOccy());
				realEstate.setWeatherDiscountedValueHuf(weatherDiscountedValueInHuf);
				final Double weatherDiscountedValueInUsd = currencyUtil.convert(realEstate.getWeatherDiscountedValueCcy(), "USD", realEstate.getWeatherDiscountedValueOccy());
				realEstate.setWeatherDiscountedValueUsd(weatherDiscountedValueInUsd);
				final Double weatherDiscountedValueInEur = currencyUtil.convert(realEstate.getWeatherDiscountedValueCcy(), "EUR", realEstate.getWeatherDiscountedValueOccy());
				realEstate.setWeatherDiscountedValueEur(weatherDiscountedValueInEur);
			}
		}
	}

}
