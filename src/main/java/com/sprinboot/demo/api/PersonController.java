package com.sprinboot.demo.api;

import com.sprinboot.demo.model.Person;
import com.sprinboot.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "/adults")
    public List<Person> getPeopleOlderThan18() {
        return personService.getPeopleOlderThan18();
    }

    @GetMapping(path = "/minors")
    public List<Person> getPeopleYoungerThan18() {
        return personService.getPeopleYoungerThan18();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id)
                .orElse(null);
    }

    @PostMapping
    public Optional<Person> addPerson(@Valid @NotNull @RequestBody Person person) {
        return personService.addPerson(person);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }


    @PutMapping(path = "{id}")
    public Optional<Person> updatePerson(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person personToUpdate) {
        return personService.updatePerson(id, personToUpdate);
    }

}
