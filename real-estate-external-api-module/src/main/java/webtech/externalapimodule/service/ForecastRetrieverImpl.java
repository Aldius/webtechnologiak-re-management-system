package webtech.externalapimodule.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import webtech.externalapimodule.model.ForecastResponse;
import webtech.externalapimodule.model.exceptions.ExternalServiceGatewayException;
import webtech.externalapimodule.model.exceptions.ExternalServiceInvocationException;

@Service
@SessionScope
@Component
public class ForecastRetrieverImpl implements ForecastRetriever {

    public static final String FORECAST_IO_SERVICE_NAME = "ForecastIOException";
    protected static final String LATITUDE = "latitude";
    protected static final String LONGITUDE = "longitude";
    protected static final String API_KEY = "apiKey";

    @Autowired
    private Environment environment;

    private String darkskyApiKey;

    @Value("${darksky.base.url}")
    private String darkskyBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDarkskyApiKey() {
        if (darkskyApiKey == null) {
            this.darkskyApiKey = environment.getProperty("DARKSKY_API_KEY");
        }
        return darkskyApiKey;
    }

    public void setDarkskyApiKey(String forecastioApiKey) {
        this.darkskyApiKey = forecastioApiKey;
    }

    public String getDarkskyBaseUrl() {
        return darkskyBaseUrl;
    }

    public void setDarkskyBaseUrl(String forecastioBaseUrl) {
        this.darkskyBaseUrl = forecastioBaseUrl;
    }

    Map<String,String> buildURLMap(String longitude, String latitude) {
            Map<String,String> arguments = new HashMap<String,String>();
            arguments.put(API_KEY, getDarkskyApiKey());
            arguments.put(LONGITUDE, longitude);
            arguments.put(LATITUDE, latitude);
            return arguments;
    }

    @Override
    public ForecastResponse getForcastFor(String longitude, String latitude) {
        try {
            ForecastResponse forecast = restTemplate.getForObject(getDarkskyBaseUrl(),
                    ForecastResponse.class, buildURLMap(longitude, latitude));
            return forecast;
        } catch (HttpStatusCodeException httpStatusEx) {
            // Darksky only return HTTPStatus code (not error response) so catch exceptions here and convert to
            // our common Exception for easier error handling
			// System.out.println("HttpStatus from ForecastIO is: " + httpStatusEx.getRawStatusCode());
            throw new ExternalServiceInvocationException(FORECAST_IO_SERVICE_NAME, httpStatusEx.getRawStatusCode());
        } catch (Exception ex) {
            // This is thrown when can't even get to API (e.g. network error)!
            throw new ExternalServiceGatewayException(FORECAST_IO_SERVICE_NAME, ex);
        }
        //return null;
    }

}