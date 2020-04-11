package com.tedu.hbt.controller;

import com.tedu.hbt.pojo.Person;
import com.tedu.hbt.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    @Autowired
    private PersonService  personService;

    @RequestMapping("/list")
    public String findAll(Map<String,Object> map){
        List<Person> list = personService.findAll();
        map.put("person",list);
        return "list";
    }

    @GetMapping("/add")
    public String addPerson(){
        return "add";
    }

    @PostMapping("/add")
    public String addPerson(Person person){
        personService.addPesron(person);
        return "redirect:/list";
    }

    @GetMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") int id,Map<String,Person> map){
        Person person = personService.getPerson(id);
        map.put("person",person);
        return "update";
    }

    @PostMapping("update")
    public String updatePerson(Person person){
        personService.updatePerson(person);
        return "redirect:/list";
    }

    @GetMapping("delete/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personService.deletePerson(id);
        return "redirect:/list";
    }

}
