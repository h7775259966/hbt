package com.tedu.hbt.service;

import com.tedu.hbt.pojo.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Person getPerson(Integer id);

    int addPesron(Person person);

    int updatePerson(Person person);

    int deletePerson(Integer id);
}
