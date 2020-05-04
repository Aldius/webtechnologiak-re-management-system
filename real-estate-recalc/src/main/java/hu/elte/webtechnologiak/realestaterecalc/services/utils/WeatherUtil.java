package hu.elte.webtechnologiak.realestaterecalc.services.utils;

import com.fasterxml.jackson.jr.ob.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.IOException;
import java.util.Map;

@Service
@ApplicationScope
public class WeatherUtil {

	private final HttpUtil httpUtil;

	@Autowired
	public WeatherUtil( final HttpUtil httpUtil ) {
		this.httpUtil = httpUtil;
	}

	public Double getWindSpeed( final Double lat, final Double lng ) throws IOException {
		final String url = "http://real-estate-external-api/externalapis/forecast/" + lat + "," + lng;
		final String response = httpUtil.sendGetRequest(url);
		final Map<String, Object> rootJsonContent = (Map<String, Object>) JSON.std.anyFrom(response);
		final Map<String, Double> currently = (Map<String, Double>) rootJsonContent.get("currently");
		return currently.get("windSpeed");
	}

}
