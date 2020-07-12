package com.challenge.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column @NotNull @Size(max = 100)
    private String fullName;
    @Column @NotNull @Size(max = 100)
    @Email
    private String email;
    @Column @NotNull @Size(max = 50)
    private String nickname;
    @Column @NotNull @Size(max = 255)
    private String password;
    @CreatedDate
    @Column @NotNull
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "idCandidate")
    List<Candidate> candidates;

    @OneToMany(mappedBy = "idSubmission")
    List<Submission> submissions;



}
