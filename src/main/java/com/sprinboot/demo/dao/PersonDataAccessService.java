package com.sprinboot.demo.dao;

import com.sprinboot.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Person> insertPerson(UUID id, Person person) {
        Person newPerson = new Person(id, person.getName(), person.getEmail(), person.getDob(), person.getLocation());
        jdbcTemplate.update("INSERT INTO person (id, name, email, dob, age) VALUES (?, ?, ?, ?, ?)",
                newPerson.getId(), newPerson.getName(), newPerson.getEmail(), newPerson.getDob(), newPerson.getLocation());

        return Optional.of(new Person(newPerson.getId(), newPerson.getName(), newPerson.getEmail(), newPerson.getDob(), newPerson.getLocation()));

    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name, email, dob, location FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            Date dob = resultSet.getDate("dob");
            String location = resultSet.getString("location");

            return new Person(id, name, email, dob, location);
        });
    }

    @Override
    public List<Person> selectPeopleOlderThan18()  {
        final String sql = "SELECT id, name, email, dob, location FROM person";
        ArrayList<Person> adultPeople = new ArrayList<>();

        jdbcTemplate.query(sql, (resultSet, i) -> {

            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            Date dob = resultSet.getDate("dob");
            String location = resultSet.getString("location");

            DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            int birthDate = Integer.parseInt(formatter.format(dob));
            int currentDate = Integer.parseInt(formatter.format(new java.util.Date()));
            int currentAge = (currentDate - birthDate) / 10000;

            if (currentAge >= 18) {
                adultPeople.add(new Person(id, name, email, dob, location));
            }

            return 0;

        });

        return adultPeople;
    }

    @Override
    public List<Person> selectPeopleYoungerThan18()  {
        final String sql = "SELECT id, name, email, dob, location FROM person";
        ArrayList<Person> minorPeople = new ArrayList<>();

        jdbcTemplate.query(sql, (resultSet, i) -> {

            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            Date dob = resultSet.getDate("dob");
            String location = resultSet.getString("location");

            DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            int birthDate = Integer.parseInt(formatter.format(dob));
            int currentDate = Integer.parseInt(formatter.format(new java.util.Date()));
            int currentAge = (currentDate - birthDate) / 10000;

            if (currentAge < 18) {
                minorPeople.add(new Person(id, name, email, dob, location));
            }

            return 0;

        });

        return minorPeople;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name, email, dob, location FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            Date dob = resultSet.getDate("dob");
            String location = resultSet.getString("location");
            return new Person(personId, name, email, dob, location);
        });

        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM person WHERE id = ?";
        Object[] person = new Object[]{id};
        jdbcTemplate.update(sql, person);
        return 1;
    }

    @Override
    public Optional<Person> updatePersonById(UUID id, Person person) {
        final String sql = "UPDATE person SET name = ?, email = ?, dob = ?, location = ?  WHERE id = ?";
        jdbcTemplate.update(sql, person.getName(), person.getEmail(), person.getDob(), person.getLocation(), id);

        return Optional.of(person);
    }
}
