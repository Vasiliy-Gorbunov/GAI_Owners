package com.gai_app.gai_owners.repository;

import com.gai_app.gai_owners.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerJpaRepository extends OwnerRepository, JpaRepository<Owner, Long> {


    List<Owner> findAll();

    Optional<Owner> findById(Long id);

    Owner save(Owner owner);

    void deleteById(Long id);
}
