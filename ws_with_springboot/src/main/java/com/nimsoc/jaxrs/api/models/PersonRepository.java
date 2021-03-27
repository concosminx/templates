package com.nimsoc.jaxrs.api.models;

import com.nimsoc.jaxrs.api.models.db.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, String> {
}
