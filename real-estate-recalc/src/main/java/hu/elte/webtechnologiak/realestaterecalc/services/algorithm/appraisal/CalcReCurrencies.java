package hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal;

import hu.elte.webtechnologiak.realestaterecalc.config.SpringContext;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.Algorithm;
import hu.elte.webtechnologiak.realestaterecalc.services.utils.CurrencyUtil;

import java.io.IOException;

public class CalcReCurrencies implements Algorithm {

	private final CurrencyUtil currencyUtil = SpringContext.getBean(CurrencyUtil.class);

	@Override
	public void execute( final BaseEntity entity ) throws IOException {
		if (entity instanceof RealEstate) {
			final RealEstate realEstate = (RealEstate) entity;
			if (realEstate.getMarketValueOccy() != null && realEstate.getMarketValueCcy() != null) {
				final Double marketValueHuf = currencyUtil.convert(realEstate.getMarketValueCcy(), "HUF", realEstate.getMarketValueOccy());
				realEstate.setMarketValueHuf(marketValueHuf);
				final Double marketValueUsd = currencyUtil.convert(realEstate.getMarketValueCcy(), "USD", realEstate.getMarketValueOccy());
				realEstate.setMarketValueUsd(marketValueUsd);
				final Double marketValueEur = currencyUtil.convert(realEstate.getMarketValueCcy(), "EUR", realEstate.getMarketValueOccy());
				realEstate.setMarketValueEur(marketValueEur);
			}
		}
	}

}
