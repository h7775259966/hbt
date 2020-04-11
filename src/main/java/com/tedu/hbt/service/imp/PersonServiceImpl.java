package com.tedu.hbt.service.imp;

import com.tedu.hbt.dao.PersonMapper;
import com.tedu.hbt.pojo.Person;
import com.tedu.hbt.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public List<Person> findAll() {
        return personMapper.findAll();
    }

    @Override
    public Person getPerson(Integer id) {
        return personMapper.getPerson(id);
    }

    @Override
    public int addPesron(Person person) {
        return personMapper.addPerson(person);
    }

    @Override
    public int updatePerson(Person person) {
        return personMapper.updatePerson(person);
    }

    @Override
    public int deletePerson(Integer id) {
        return personMapper.deletePerson(id);
    }

}
