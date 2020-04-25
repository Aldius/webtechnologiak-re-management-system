package hu.elte.webtechnologiak.realestatedocumenthandling.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.BaseEntity;
import hu.elte.webtechnologiak.realestatedocumenthandling.model.utils.DocumentType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "Documents")
public class Document extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DocumentType documentType;

    @Column(nullable = false)
    private String documentFormat;

    @Lob
    @Column(columnDefinition="MEDIUMBLOB", nullable = false)
    @JsonIgnore
    private byte[] data;

    @Column
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime uploadDate = LocalDateTime.now();

    @ManyToOne(targetEntity = DataStoreEntity.class)
    @JoinColumn(name="data_store_entity_id", referencedColumnName = "unique_id", nullable = false)
    @JsonIgnore
    private DataStoreEntity dataStoreEntity;

}
