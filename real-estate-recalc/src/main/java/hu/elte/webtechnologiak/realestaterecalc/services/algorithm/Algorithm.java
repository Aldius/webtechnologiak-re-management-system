package hu.elte.webtechnologiak.realestaterecalc.services.algorithm;

import hu.elte.webtechnologiak.realestaterecalc.model.entities.BaseEntity;

import java.io.IOException;

public interface Algorithm {

	void execute( BaseEntity entity ) throws IOException;

}
