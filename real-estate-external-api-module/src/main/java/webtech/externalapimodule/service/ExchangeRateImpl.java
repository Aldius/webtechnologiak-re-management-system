package webtech.externalapimodule.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpStatusCodeException;
import webtech.externalapimodule.model.exceptions.ExternalServiceGatewayException;
import webtech.externalapimodule.model.exceptions.ExternalServiceInvocationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.core.env.Environment;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
@SessionScope
@Component
public class ExchangeRateImpl implements ExchangeRateRetriever{

    protected static final String API_KEY = "apiKey";
    protected static final String CURRENCY = "currency";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    private String exchangeApiKey;

    @Value("${currencies.url}")
    private String exchangeUrl;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCurrenciesApiKey() {
        if (exchangeApiKey == null) {
            this.exchangeApiKey = environment.getProperty("EXC_API_KEY");
        }
        return exchangeApiKey;
    }

    public void setCurrenciesApiKey(String exchangeApiKey) {
        this.exchangeApiKey = exchangeApiKey;
    }

    public String getExcUrl() {
        return exchangeUrl;
    }

    public void setCurrencuesUrl(String exchangeUrl) {
        this.exchangeUrl = exchangeUrl;
    }

    Map<String, String> buildURLMap(String currency) {
        Map<String, String> arguments = new HashMap<String, String>();
        arguments.put(API_KEY, getCurrenciesApiKey());
        arguments.put(CURRENCY, currency);
        return arguments;
    }

    @Override
    public String getExchange(String currency) {
        try {
            String curr = restTemplate.getForObject(getExcUrl(),
                    String.class, buildURLMap(currency));
            return curr;
        } catch (HttpStatusCodeException httpStatusEx) {
            throw new ExternalServiceInvocationException("currencies failed", httpStatusEx.getRawStatusCode());
        } catch (Exception ex) {
            throw new ExternalServiceGatewayException("currencies api failed", ex);
        }

    }
}