package com.challenge.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class SubmissionPK implements Serializable {

    @ManyToOne
    private User userId;

    @ManyToOne
    private Challenge challengeId;
}
