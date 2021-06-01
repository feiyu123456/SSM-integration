package com.feiyu.service;

import com.feiyu.dao.PersonDao;
import com.feiyu.pojo.Person;
import org.springframework.stereotype.Service;

import java.util.List;

public class PersonServiceImpl implements PersonService{

    private PersonDao personDao;


    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public List<Person> selectPersons() {
        return personDao.selectPersons();
    }

    @Override
    public int updatePerson(Person person) {
        return personDao.updatePerson(person);
    }

    @Override
    public Person selectPerson(int pno) {
        return personDao.selectPerson(pno);
    }
}
