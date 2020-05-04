package hu.elte.webtechnologiak.realestaterecalc.services.utils;

import com.fasterxml.jackson.jr.ob.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.IOException;
import java.util.Map;

@Service
@ApplicationScope
public class GeocodeUtil {

	private final HttpUtil httpUtil;

	@Autowired
	public GeocodeUtil( final HttpUtil httpUtil ) {
		this.httpUtil = httpUtil;
	}

	public Pair<Double, Double> getCoordinates(final String country, final String city, final String street, final String streetNumber, final String zipCode ) throws IOException {
		final String url = "http://real-estate-external-api/externalapis/location/"+ city + ","+ street + "+" +streetNumber + "," + zipCode;
		final String response = httpUtil.sendGetRequest(url);
		final Map<String, Double> rootJsonContent = (Map<String, Double>) JSON.std.anyFrom(response);
		final Double lat = rootJsonContent.get("lat");
		final Double lng = rootJsonContent.get("lng");
		return Pair.of(lat, lng);
	}

}
