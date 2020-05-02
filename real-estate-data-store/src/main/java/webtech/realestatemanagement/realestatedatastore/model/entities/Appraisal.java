package webtech.realestatemanagement.realestatedatastore.model.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import webtech.realestatemanagement.realestatedatastore.model.BaseEntity;
import webtech.realestatemanagement.realestatedatastore.model.utils.ValuationType;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime appraisalDate;

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
