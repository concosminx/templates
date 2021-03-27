package com.nimsoc.jaxrs.api.mapper;

import com.nimsoc.jaxrs.api.models.db.PersonEntity;
import com.nimsoc.jaxrs.lib.models.Person;

public class PersonMapper {

  public static Person toPerson(PersonEntity entity) {

    if (entity == null) {
      return null;
    }

    Person p = new Person();
    p.setId(entity.getId());
    p.setUpdatedAt(entity.getUpdatedAt());
    p.setCreatedAt(entity.getCreatedAt());
    p.setName(entity.getName());
    p.setAge(entity.getAge());
    p.setGender(entity.getGender());
    return p;
  }

  public static PersonEntity toEntity(Person p) {

    if (p == null) {
      return null;
    }

    PersonEntity entity = new PersonEntity();
    entity.setId(p.getId());
    entity.setName(p.getName());
    entity.setAge(p.getAge());
    entity.setGender(p.getGender());

    return entity;
  }
}
