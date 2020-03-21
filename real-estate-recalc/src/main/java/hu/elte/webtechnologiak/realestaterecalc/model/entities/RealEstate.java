package hu.elte.webtechnologiak.realestaterecalc.model.entities;

import hu.elte.webtechnologiak.realestaterecalc.model.BaseEntity;
import hu.elte.webtechnologiak.realestaterecalc.model.utils.RealEstateMainType;
import hu.elte.webtechnologiak.realestaterecalc.model.utils.RealEstateSubType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "RealEstates")
public class RealEstate extends BaseEntity {

    @Column(nullable = false)
    private String address;

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
