package com.egartechnology.fullstack.repository;

import com.egartechnology.fullstack.entities.SecuritiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuritiesRepository extends JpaRepository<SecuritiesEntity, Long> {

  SecuritiesEntity findByUuid(String uuid);
}
