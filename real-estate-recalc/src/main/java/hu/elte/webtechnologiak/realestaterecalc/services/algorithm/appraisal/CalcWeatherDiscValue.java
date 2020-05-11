package hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal;

import hu.elte.webtechnologiak.realestaterecalc.config.SpringContext;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.Algorithm;
import hu.elte.webtechnologiak.realestaterecalc.services.utils.WeatherUtil;

import java.io.IOException;

public class CalcWeatherDiscValue implements Algorithm {

	private final WeatherUtil weatherUtil = SpringContext.getBean(WeatherUtil.class);

	@Override
	public void execute( final BaseEntity entity ) throws IOException {
		if (entity instanceof RealEstate) {
			final RealEstate realEstate = (RealEstate) entity;
			final Double windSpeed = weatherUtil.getWindSpeed(realEstate.getLatitude(), realEstate.getLongitude());
			final Double discountMultiplier;
			if (windSpeed < 2) {
				discountMultiplier = 1.0;
			} else if (windSpeed < 10) {
				discountMultiplier = 0.9;
			} else {
				discountMultiplier = 0.8;
			}
			realEstate.setWeatherDiscountedValueOccy(realEstate.getMarketValueOccy() * discountMultiplier);
			realEstate.setWeatherDiscountedValueCcy(realEstate.getMarketValueCcy());
		}
	}

}
