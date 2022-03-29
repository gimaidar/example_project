package com.gimaletdinov.exampleProject.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDate;

@Entity
@Table(name = "Document")
@Data
@NoArgsConstructor
public class Document {

    @Id
    @Column(name = "id")
    private Integer id;

    @Version
    private Integer version;

    @Column(name = "doc_number")
    private Integer number;

    @Column(name = "doc_date")
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id")
    private DocumentType documentType;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

}
