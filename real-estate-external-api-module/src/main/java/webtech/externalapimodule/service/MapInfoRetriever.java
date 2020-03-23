package webtech.externalapimodule.service;

import webtech.externalapimodule.model.MapAPIResponse;

public interface MapInfoRetriever {

    public MapAPIResponse getMapInfoFor(String city, String publicSpace, String state);

}