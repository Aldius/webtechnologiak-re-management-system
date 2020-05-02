package webtech.realestatemanagement.realestatedatastore.model.entities.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppraisalDto {

	private String uniqueId;

	private String realEstateUniqueId;

	private Date appraisalDate;

	private Double appraisedMarketValueOfBuildingsOccy;

	private String appraisedMarketValueOfBuildingsCcy;

	private Double appraisedMarketValueOfLandOccy;

	private String appraisedMarketValueOfLandCcy;

}
