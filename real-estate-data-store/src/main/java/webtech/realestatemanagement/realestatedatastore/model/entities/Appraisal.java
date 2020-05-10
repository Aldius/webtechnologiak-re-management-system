package webtech.realestatemanagement.realestatedatastore.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import webtech.realestatemanagement.realestatedatastore.model.BaseEntity;
import webtech.realestatemanagement.realestatedatastore.model.utils.ValuationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "Appraisals")
public class Appraisal extends BaseEntity {

	@Column
	private String appraisalName;

    @Column
	private Date appraisalDate;

	@Column(nullable = false)
	private String realEstateId;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private ValuationType valuationType;

	private Double appraisedMarketValueOfBuildingsOccy;

	private String appraisedMarketValueOfBuildingsCcy;

	private Double appraisedMarketValueOfLandOccy;

	private String appraisedMarketValueOfLandCcy;

}
