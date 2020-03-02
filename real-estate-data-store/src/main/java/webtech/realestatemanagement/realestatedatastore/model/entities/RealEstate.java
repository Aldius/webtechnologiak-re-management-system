package webtech.realestatemanagement.realestatedatastore.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import webtech.realestatemanagement.realestatedatastore.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "RealEstates")
public class RealEstate extends BaseEntity {

    @Column
    private String address;

    @Column
    private double price;

    @Column
    private double size;
}
