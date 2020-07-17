package com.challenge.repository;

import com.challenge.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u FROM User u JOIN u.candidates ca JOIN ca.id.acceleration acc " +
            "WHERE acc.name = ?1")
    List<User> findByCandidateIdAccelerationName(String name);

    @Query("select u FROM User u JOIN u.candidates ca JOIN ca.id.company co " +
            "WHERE co.id = ?1")
    List<User> findByCandidateIdCompanyId(Long idCompany);



}
