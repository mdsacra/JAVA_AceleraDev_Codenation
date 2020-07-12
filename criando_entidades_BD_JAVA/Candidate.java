package com.challenge.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data @AllArgsConstructor @NoArgsConstructor
public class Candidate{

    @EmbeddedId
    private CandidatePK idCandidate;

    @NotNull
    private Integer status;

    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;


}
