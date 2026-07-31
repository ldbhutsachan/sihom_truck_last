package com.ldb.truck.Entity.Staff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "candidate_language")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "language_name")
    private String languageName;

    @Column(name = "speaking")
    private String speaking;

    @Column(name = "reading")
    private String reading;

    @Column(name = "writing")
    private String writing;

    @Column(name = "listening")
    private String listening;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
    private CandidateProfile candidate;
}
