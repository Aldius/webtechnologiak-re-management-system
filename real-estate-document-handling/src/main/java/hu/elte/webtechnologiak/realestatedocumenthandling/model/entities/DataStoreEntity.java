package hu.elte.webtechnologiak.realestatedocumenthandling.model.entities;

import hu.elte.webtechnologiak.realestatedocumenthandling.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "DataStoreEntities")
public class DataStoreEntity extends BaseEntity {

    @Id
    @Column(name = "unique_id")
    protected String uniqueId;

    @OneToMany(targetEntity=Document.class, mappedBy="dataStoreEntity",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Document> documents = new ArrayList<>();

    public List<Document> getDocuments() {
        return documents.stream().filter(doc -> doc.getStatus() == BaseEntity.ACTIVE_ENTITY_STATUS).collect(Collectors.toList());
    }
}
