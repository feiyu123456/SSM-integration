package com.feiyu.dao;

import com.feiyu.pojo.Person;

import java.util.List;

public interface PersonDao {

    List<Person> selectPersons();

    int updatePerson(Person person);

    Person selectPerson(int pno);
}
