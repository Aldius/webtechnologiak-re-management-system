package webtech.externalapimodule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import webtech.externalapimodule.model.CurrentForecast;
import webtech.externalapimodule.model.ForecastResponse;
import webtech.externalapimodule.service.ForecastRetriever;

import webtech.externalapimodule.model.Location;
import webtech.externalapimodule.model.MapAPIResponse;
import webtech.externalapimodule.service.MapInfoRetriever;


import webtech.externalapimodule.service.ExchangeRateRetriever;

@RestController
@RequestMapping("/externalapis")
public class RealEstateController {


    @Autowired
    private ForecastRetriever forecastRetriever;

    @Autowired
    private MapInfoRetriever mapInfoRetriever;

    @Autowired
    private ExchangeRateRetriever exchangeRateRetriever;

    public MapInfoRetriever getMapInfoRetriever() {
        return mapInfoRetriever;
    }

    public void setMapInfoRetriever(MapInfoRetriever mapInfoRetriever) {
        this.mapInfoRetriever = mapInfoRetriever;
    }

    public ForecastRetriever getForecastRetriever() {
        return forecastRetriever;
    }

    public void setForecastRetriever(ForecastRetriever forecastRetriever) {
        this.forecastRetriever = forecastRetriever;
    }

    public ExchangeRateRetriever getExchangeRateRetriever() {
        return exchangeRateRetriever;
    }

    public void setExchangeRateRetriever(ExchangeRateRetriever exchangeRateRetriever) {
        this.exchangeRateRetriever = exchangeRateRetriever;
    }
    /*
    @GetMapping("/all")
    public ResponseEntity<String> listAll() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }*/

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getIndexPage() {
        ModelAndView modelAndView = new ModelAndView("CurrentForecast");
        return modelAndView;
    }

    @RequestMapping(value = "/forecast/{lat},{lng}", method=RequestMethod.GET, produces="application/json")
    public ForecastResponse getForecast(@PathVariable("lat") String lat,
                                        @PathVariable("lng") String lng) {
        //MapAPIResponse mapResponse = this.getMapInfoRetriever().getMapInfoFor(city, state);
        //Location loc = mapResponse.getGeometry().getLocation();
        ForecastResponse forecastResponse = forecastRetriever.getForcastFor(String.valueOf(lat),
                String.valueOf(lng));
        //forecastResponse.setFormattedAddress(mapResponse.getFormattedAddress());
        //forecastResponse.setSearchAddress(buildSearchAddress(city,state));
        return forecastResponse;
    }

    //in the url use "+" between place names, i.e. location/Budapest,Pázmány+Péter+stny.+1c,1117
    @RequestMapping(value = "/location/{city},{publicSpace},{state}", method=RequestMethod.GET, produces="application/json")
    public ForecastResponse getLocationWithForecast(@PathVariable("city") String city,
                                        @PathVariable("publicSpace") String publicSpace,
                                        @PathVariable("state") String state) {
        MapAPIResponse mapResponse = this.getMapInfoRetriever().getMapInfoFor(city, publicSpace, state);
        Location loc = mapResponse.getGeometry().getLocation();
        ForecastResponse forecastResponse = forecastRetriever.getForcastFor(String.valueOf(loc.getLatitude()),
                String.valueOf(loc.getLongitude()));
        forecastResponse.setFormattedAddress(mapResponse.getFormattedAddress());
        forecastResponse.setSearchAddress(buildSearchAddress(city,state));
        return forecastResponse;
    }


    String buildSearchAddress(String city, String state) {
        StringBuilder builder = new StringBuilder();
        builder.append(city);
        builder.append(",");
        builder.append(state);
        return builder.toString();
    }

    @RequestMapping(value = "/currencies/{currency}", method=RequestMethod.GET, produces="application/json")
    public String getCurrencies(@PathVariable("currency") String currency) {
        String exchangeRateResponse = exchangeRateRetriever.getExchange(String.valueOf(currency));
        return exchangeRateResponse;
    }

}
