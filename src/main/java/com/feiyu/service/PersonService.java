package com.feiyu.service;

import com.feiyu.base.BaseDaoImpl;
import com.feiyu.pojo.Person;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PersonService {

    /*private static final String SQL_PACKAGE = "com.feiyu.dao.PersonMapper";*/

    List<Person> selectPersons();

    int updatePerson(Person person);

    Person selectPerson(int pno);

}
