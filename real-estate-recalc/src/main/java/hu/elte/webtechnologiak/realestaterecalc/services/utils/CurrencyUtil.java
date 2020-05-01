package hu.elte.webtechnologiak.realestaterecalc.services.utils;

import com.fasterxml.jackson.jr.ob.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.IOException;
import java.util.Map;

@Service
@ApplicationScope
public class CurrencyUtil {

	private final HttpUtil httpUtil;

	@Autowired
	public CurrencyUtil( final HttpUtil httpUtil ) {
		this.httpUtil = httpUtil;
	}

	public Double convert( final String from, final String to, final Double amount ) throws IOException {
		final String url = "http://real-estate-external-api/externalapis/currencies/" + from;
		final String currencyRates = httpUtil.sendGetRequest(url);
		final Map<String, Object> rootJsonContent = (Map<String, Object>) JSON.std.anyFrom(currencyRates);
		Map<String, Object> conversionRates = (Map<String, Object>) rootJsonContent.get("conversion_rates");
		Object conversionRate = conversionRates.get(to);
		if (conversionRate instanceof Double) {
			final Double conversionRateD = (Double) conversionRate;
			return amount * conversionRateD;
		} else if (conversionRate instanceof Integer) {
			final Integer conversionRateI = (Integer) conversionRate;
			return amount * conversionRateI;
		}
		throw new IllegalStateException();
	}

}
