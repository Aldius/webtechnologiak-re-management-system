package hu.elte.webtechnologiak.realestatedocumenthandling.model;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class BaseEntity {

    public static final int ACTIVE_ENTITY_STATUS = 0;
    public static final int INACTIVE_ENTITY_STATUS = 1;

    @Version
    protected int version;

    @Column
    protected int status;
}
