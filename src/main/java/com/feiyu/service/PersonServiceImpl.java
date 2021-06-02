package com.feiyu.service;

import com.feiyu.dao.PersonDao;
import com.feiyu.exception.UpdatePersonTempException;
import com.feiyu.pojo.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public int deletePerson(int pno) {
        return personDao.deletePerson(pno);
    }

    @Override
    public int insertPerson(Person person) {
        return personDao.insertPerson(person);
    }

    //@Transactional(propagation = Propagation.REQUIRED ,rollbackFor=Exception.class)
    public void updatePersonTemp() throws UpdatePersonTempException{
        this.deletePerson(1011);
        int temp = this.insertPerson(new Person(1001, "千寻88", "朝阳区nb", "dddddddddd"));
        if (!(temp > 0)){
            throw new UpdatePersonTempException("用户插入失败失败rollback！");
        }
    }
}
