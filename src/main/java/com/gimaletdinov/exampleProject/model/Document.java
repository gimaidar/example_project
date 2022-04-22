package com.gimaletdinov.exampleProject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDate;

/**
 * Сущность Документ
 */
@Entity
@Table(name = "Document")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "user")
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_type_id")
    private DocumentType documentType;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

}
