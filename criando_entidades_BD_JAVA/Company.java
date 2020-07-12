package com.challenge.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data @AllArgsConstructor @NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column @NotNull @Size(max = 100)
    private String name;
    @Column @NotNull @Size(max = 50)
    private String slug;
    @CreatedDate
    @Column @NotNull
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "company_id")
    private List<Candidate> candidates;

}
