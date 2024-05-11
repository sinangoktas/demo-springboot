package com.sprinboot.demo.dao;

import com.sprinboot.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    Optional<Person> insertPerson(UUID id, Person person);

    default Optional<Person> insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();

    List<Person> selectPeopleOlderThan18();

    List<Person> selectPeopleYoungerThan18();

    Optional<Person> selectPersonById(UUID id);

    Optional<Person> updatePersonById(UUID id, Person person);

    int deletePersonById(UUID id);

}
