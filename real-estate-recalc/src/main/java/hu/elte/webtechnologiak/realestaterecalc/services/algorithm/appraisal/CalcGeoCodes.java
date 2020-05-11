package hu.elte.webtechnologiak.realestaterecalc.services.algorithm.appraisal;

import hu.elte.webtechnologiak.realestaterecalc.config.SpringContext;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.entities.RealEstate;
import hu.elte.webtechnologiak.realestaterecalc.services.algorithm.Algorithm;
import hu.elte.webtechnologiak.realestaterecalc.services.utils.GeocodeUtil;
import org.springframework.data.util.Pair;

import java.io.IOException;

public class CalcGeoCodes implements Algorithm {

	private final GeocodeUtil geocodeUtil = SpringContext.getBean(GeocodeUtil.class);

	@Override
	public void execute( final BaseEntity entity ) throws IOException {
		if (entity instanceof RealEstate) {
			final RealEstate realEstate = (RealEstate) entity;
			if (realEstate.getLatitude() == null && realEstate.getLongitude() == null) {
				final Pair<Double, Double> coordinates = geocodeUtil.getCoordinates(realEstate.getCountry(), realEstate.getCity(), realEstate.getStreet(), realEstate.getStreetNumber(), realEstate.getZipCode());
				realEstate.setLatitude(coordinates.getFirst());
				realEstate.setLongitude(coordinates.getSecond());
			}
		}
	}

}
