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
	private Double marketValueBccy;

	@Basic
	private Double marketValueUsd;

	@Basic
	private Double marketValueEur;

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

	public Double getMarketValueBccy() {
		return marketValueBccy;
	}

	public void setMarketValueBccy( Double marketValueBccy ) {
		this.marketValueBccy = marketValueBccy;
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

	@Override
	public String toString() {
		return "RealEstate{" +
			       "marketValueOccy=" + marketValueOccy +
			       ", marketValueCcy='" + marketValueCcy + '\'' +
			       ", marketValueBccy=" + marketValueBccy +
			       ", marketValueUsd=" + marketValueUsd +
			       ", marketValueEur=" + marketValueEur +
			       ", version=" + version +
			       ", status=" + status +
			       '}';
	}

}
