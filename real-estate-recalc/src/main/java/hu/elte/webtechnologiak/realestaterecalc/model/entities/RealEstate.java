package hu.elte.webtechnologiak.realestaterecalc.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "T_REALESTATE")
public class RealEstate extends BaseEntity {

	@JsonIgnore
	@OneToMany(mappedBy = "realEstate")
	private List<Appraisal> appraisals;

	@Basic
	private Double marketValueOccy;

	@Basic
	private String marketValueCcy;

	@Basic
	private Double marketValueHuf;

	@Basic
	private Double marketValueUsd;

	@Basic
	private Double marketValueEur;

	@Basic
	private Double longitude;

	@Basic
	private Double latitude;

	@Basic
	private String country;

	@Basic
	private String city;

	@Basic
	private String street;

	@Basic
	private String streetNumber;

	@Basic
	private String zipCode;

	public List<Appraisal> getAppraisals() {
		return appraisals;
	}

	public void setAppraisals( List<Appraisal> appraisals ) {
		this.appraisals = appraisals;
	}

	public Double getMarketValueOccy() {
		return marketValueOccy;
	}

	public void setMarketValueOccy( Double marketValueOccy ) {
		this.marketValueOccy = marketValueOccy;
	}

	public String getMarketValueCcy() {
		return marketValueCcy;
	}

	public void setMarketValueCcy( String marketValueCcy ) {
		this.marketValueCcy = marketValueCcy;
	}

	public Double getMarketValueHuf() {
		return marketValueHuf;
	}

	public void setMarketValueHuf( Double marketValueHuf ) {
		this.marketValueHuf = marketValueHuf;
	}

	public Double getMarketValueUsd() {
		return marketValueUsd;
	}

	public void setMarketValueUsd( Double marketValueUsd ) {
		this.marketValueUsd = marketValueUsd;
	}

	public Double getMarketValueEur() {
		return marketValueEur;
	}

	public void setMarketValueEur( Double marketValueEur ) {
		this.marketValueEur = marketValueEur;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude( Double longitude ) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude( Double latitude ) {
		this.latitude = latitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry( String country ) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity( String city ) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet( String street ) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber( String streetNumber ) {
		this.streetNumber = streetNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode( String zipCode ) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "RealEstate{" +
			       "marketValueOccy=" + marketValueOccy +
			       ", marketValueCcy='" + marketValueCcy + '\'' +
			       ", marketValueHuf=" + marketValueHuf +
			       ", marketValueUsd=" + marketValueUsd +
			       ", marketValueEur=" + marketValueEur +
			       ", longitude=" + longitude +
			       ", latitude=" + latitude +
			       ", country='" + country + '\'' +
			       ", city='" + city + '\'' +
			       ", street='" + street + '\'' +
			       ", streetNumber='" + streetNumber + '\'' +
			       ", zipCode='" + zipCode + '\'' +
			       ", status=" + status +
			       '}';
	}

}
