package com.sprinboot.demo.service;

import com.sprinboot.demo.dao.PersonDao;
import com.sprinboot.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public Optional<Person>  addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public List<Person> getPeopleOlderThan18() {
        return personDao.selectPeopleOlderThan18();
    }

    public List<Person> getPeopleYoungerThan18() {
        return personDao.selectPeopleYoungerThan18();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePersonById(id);
    }

    public Optional<Person> updatePerson(UUID id, Person newPerson) {
        return personDao.updatePersonById(id, newPerson);

    }

}