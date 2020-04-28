package hu.elte.webtechnologiak.realestaterecalc.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "T_APPRAISAL")
public class Appraisal extends BaseEntity {

	@JsonIgnore
	@ManyToOne
	private RealEstate realEstate;

	@Transient
	private String realEstateUniqueId;

	@Basic
	private Date appraisalDate;

	@Basic
	private Double appraisedMarketValueOfBuildingsOccy;

	@Basic
	private String appraisedMarketValueOfBuildingsCcy;

	@Basic
	private Double appraisedMarketValueOfBuildingsHuf;

	@Basic
	private Double appraisedMarketValueOfBuildingsUsd;

	@Basic
	private Double appraisedMarketValueOfBuildingsEur;

	@Basic
	private Double appraisedMarketValueOfLandOccy;

	@Basic
	private String appraisedMarketValueOfLandCcy;

	@Basic
	private Double appraisedMarketValueOfLandHuf;

	@Basic
	private Double appraisedMarketValueOfLandUsd;

	@Basic
	private Double appraisedMarketValueOfLandEur;

	@Basic
	private Double totalAppraisedValue;

	@Basic
	private String totalAppraisedValueCcy;

	@Basic
	private Double totalAppraisedValueHuf;

	@Basic
	private Double totalAppraisedValueUsd;

	@Basic
	private Double totalAppraisedValueEur;

	public RealEstate getRealEstate() {
		return realEstate;
	}

	public void setRealEstate( RealEstate realEstate ) {
		this.realEstate = realEstate;
	}

	public String getRealEstateUniqueId() {
		return realEstateUniqueId;
	}

	public void setRealEstateUniqueId( String realEstateUniqueId ) {
		this.realEstateUniqueId = realEstateUniqueId;
	}

	public Date getAppraisalDate() {
		return appraisalDate;
	}

	public void setAppraisalDate( Date appraisalDate ) {
		this.appraisalDate = appraisalDate;
	}

	public Double getAppraisedMarketValueOfBuildingsOccy() {
		return appraisedMarketValueOfBuildingsOccy;
	}

	public void setAppraisedMarketValueOfBuildingsOccy( Double appraisedMarketValueOfBuildingsOccy ) {
		this.appraisedMarketValueOfBuildingsOccy = appraisedMarketValueOfBuildingsOccy;
	}

	public String getAppraisedMarketValueOfBuildingsCcy() {
		return appraisedMarketValueOfBuildingsCcy;
	}

	public void setAppraisedMarketValueOfBuildingsCcy( String appraisedMarketValueOfBuildingsCcy ) {
		this.appraisedMarketValueOfBuildingsCcy = appraisedMarketValueOfBuildingsCcy;
	}

	public Double getAppraisedMarketValueOfBuildingsHuf() {
		return appraisedMarketValueOfBuildingsHuf;
	}

	public void setAppraisedMarketValueOfBuildingsHuf( Double appraisedMarketValueOfBuildingsHuf ) {
		this.appraisedMarketValueOfBuildingsHuf = appraisedMarketValueOfBuildingsHuf;
	}

	public Double getAppraisedMarketValueOfBuildingsUsd() {
		return appraisedMarketValueOfBuildingsUsd;
	}

	public void setAppraisedMarketValueOfBuildingsUsd( Double appraisedMarketValueOfBuildingsUsd ) {
		this.appraisedMarketValueOfBuildingsUsd = appraisedMarketValueOfBuildingsUsd;
	}

	public Double getAppraisedMarketValueOfBuildingsEur() {
		return appraisedMarketValueOfBuildingsEur;
	}

	public void setAppraisedMarketValueOfBuildingsEur( Double appraisedMarketValueOfBuildingsEur ) {
		this.appraisedMarketValueOfBuildingsEur = appraisedMarketValueOfBuildingsEur;
	}

	public Double getAppraisedMarketValueOfLandOccy() {
		return appraisedMarketValueOfLandOccy;
	}

	public void setAppraisedMarketValueOfLandOccy( Double appraisedMarketValueOfLandOccy ) {
		this.appraisedMarketValueOfLandOccy = appraisedMarketValueOfLandOccy;
	}

	public String getAppraisedMarketValueOfLandCcy() {
		return appraisedMarketValueOfLandCcy;
	}

	public void setAppraisedMarketValueOfLandCcy( String appraisedMarketValueOfLandCcy ) {
		this.appraisedMarketValueOfLandCcy = appraisedMarketValueOfLandCcy;
	}

	public Double getAppraisedMarketValueOfLandHuf() {
		return appraisedMarketValueOfLandHuf;
	}

	public void setAppraisedMarketValueOfLandHuf( Double appraisedMarketValueOfLandHuf ) {
		this.appraisedMarketValueOfLandHuf = appraisedMarketValueOfLandHuf;
	}

	public Double getAppraisedMarketValueOfLandUsd() {
		return appraisedMarketValueOfLandUsd;
	}

	public void setAppraisedMarketValueOfLandUsd( Double appraisedMarketValueOfLandUsd ) {
		this.appraisedMarketValueOfLandUsd = appraisedMarketValueOfLandUsd;
	}

	public Double getAppraisedMarketValueOfLandEur() {
		return appraisedMarketValueOfLandEur;
	}

	public void setAppraisedMarketValueOfLandEur( Double appraisedMarketValueOfLandEur ) {
		this.appraisedMarketValueOfLandEur = appraisedMarketValueOfLandEur;
	}

	public Double getTotalAppraisedValue() {
		return totalAppraisedValue;
	}

	public void setTotalAppraisedValue( Double totalAppraisedValue ) {
		this.totalAppraisedValue = totalAppraisedValue;
	}

	public String getTotalAppraisedValueCcy() {
		return totalAppraisedValueCcy;
	}

	public void setTotalAppraisedValueCcy( String totalAppraisedValueCcy ) {
		this.totalAppraisedValueCcy = totalAppraisedValueCcy;
	}

	public Double getTotalAppraisedValueHuf() {
		return totalAppraisedValueHuf;
	}

	public void setTotalAppraisedValueHuf( Double totalAppraisedValueHuf ) {
		this.totalAppraisedValueHuf = totalAppraisedValueHuf;
	}

	public Double getTotalAppraisedValueUsd() {
		return totalAppraisedValueUsd;
	}

	public void setTotalAppraisedValueUsd( Double totalAppraisedValueUsd ) {
		this.totalAppraisedValueUsd = totalAppraisedValueUsd;
	}

	public Double getTotalAppraisedValueEur() {
		return totalAppraisedValueEur;
	}

	public void setTotalAppraisedValueEur( Double totalAppraisedValueEur ) {
		this.totalAppraisedValueEur = totalAppraisedValueEur;
	}

	@Override
	public String toString() {
		return "Appraisal{" +
			       "realEstateUniqueId='" + realEstateUniqueId + '\'' +
			       ", appraisalDate=" + appraisalDate +
			       ", appraisedMarketValueOfBuildingsOccy=" + appraisedMarketValueOfBuildingsOccy +
			       ", appraisedMarketValueOfBuildingsCcy='" + appraisedMarketValueOfBuildingsCcy + '\'' +
			       ", appraisedMarketValueOfBuildingsHuf=" + appraisedMarketValueOfBuildingsHuf +
			       ", appraisedMarketValueOfBuildingsUsd=" + appraisedMarketValueOfBuildingsUsd +
			       ", appraisedMarketValueOfBuildingsEur=" + appraisedMarketValueOfBuildingsEur +
			       ", appraisedMarketValueOfLandOccy=" + appraisedMarketValueOfLandOccy +
			       ", appraisedMarketValueOfLandCcy='" + appraisedMarketValueOfLandCcy + '\'' +
			       ", appraisedMarketValueOfLandHuf=" + appraisedMarketValueOfLandHuf +
			       ", appraisedMarketValueOfLandUsd=" + appraisedMarketValueOfLandUsd +
			       ", appraisedMarketValueOfLandEur=" + appraisedMarketValueOfLandEur +
			       ", totalAppraisedValue=" + totalAppraisedValue +
			       ", totalAppraisedValueCcy='" + totalAppraisedValueCcy + '\'' +
			       ", totalAppraisedValueHuf=" + totalAppraisedValueHuf +
			       ", totalAppraisedValueUsd=" + totalAppraisedValueUsd +
			       ", totalAppraisedValueEur=" + totalAppraisedValueEur +
			       ", version=" + version +
			       ", status=" + status +
			       '}';
	}

}
