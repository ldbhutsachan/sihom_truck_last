package com.ldb.truck.Entity.Staff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "candidate_education")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level")
    private String level;

    @Column(name = "institute")
    private String institute;

    @Column(name = "year")
    private String year;

    @Column(name = "gpa")
    private String gpa;

    @Column(name = "major")
    private String major;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
    private CandidateProfile candidate;
}
