package hu.elte.webtechnologiak.realestaterecalc.model.entities;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Objects;

@MappedSuperclass
public class BaseEntity {

	public static final int ACTIVE_ENTITY_STATUS = 0;

	public static final int INACTIVE_ENTITY_STATUS = 1;

	@Version
	protected int version;

	@Basic
	protected int status;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Basic
	private String uniqueId;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion( int version ) {
		this.version = version;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus( int status ) {
		this.status = status;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId( String uniqueId ) {
		this.uniqueId = uniqueId;
	}

	@Override
	public boolean equals( Object o ) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BaseEntity that = (BaseEntity) o;
		return id.equals(that.id) &&
			       uniqueId.equals(that.uniqueId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, uniqueId);
	}

	@Override
	public String toString() {
		return "BaseEntity{" +
			       "id=" + id +
			       ", version=" + version +
			       ", status=" + status +
			       ", uniqueId='" + uniqueId + '\'' +
			       '}';
	}

}
