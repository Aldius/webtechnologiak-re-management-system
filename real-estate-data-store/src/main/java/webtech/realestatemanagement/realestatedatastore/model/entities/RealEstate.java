package webtech.realestatemanagement.realestatedatastore.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import webtech.realestatemanagement.realestatedatastore.model.BaseEntity;
import webtech.realestatemanagement.realestatedatastore.model.utils.RealEstateMainType;
import webtech.realestatemanagement.realestatedatastore.model.utils.RealEstateSubType;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "RealEstates")
public class RealEstate extends BaseEntity {

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column
    private String street;

    @Column
    private String streetNumber;

    @Column
    private String zipCode;

    @Column
    private double livingArea;

    @Column
    private double landArea;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private RealEstateMainType mainType;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private RealEstateSubType subType;

    @Column
    private int constructionYear;
}
