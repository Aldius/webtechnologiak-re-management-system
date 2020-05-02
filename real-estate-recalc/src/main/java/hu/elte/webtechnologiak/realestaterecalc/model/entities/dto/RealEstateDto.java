package hu.elte.webtechnologiak.realestaterecalc.model.entities.dto;

import lombok.Data;

@Data
public class RealEstateDto {

	private String uniqueId;

	private String country;

	private String city;

	private String street;

	private String streetNumber;

	private String zipCode;

}
