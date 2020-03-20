package webtech.externalapimodule.service;

import webtech.externalapimodule.model.ForecastResponse;

public interface ForecastRetriever {

    public ForecastResponse getForcastFor(String longitude, String latitude);

}