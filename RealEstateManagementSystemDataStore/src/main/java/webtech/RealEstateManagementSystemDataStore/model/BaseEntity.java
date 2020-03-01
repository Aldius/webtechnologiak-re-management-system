package webtech.RealEstateManagementSystemDataStore.model;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected long id;

    @Version
    protected int version;

    @Column
    protected int status;
}
