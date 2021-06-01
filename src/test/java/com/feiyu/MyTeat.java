package com.feiyu;

import com.alibaba.fastjson.JSON;
import com.feiyu.pojo.Person;
import com.feiyu.service.PersonService;
import com.feiyu.utils.DBContextHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public class MyTeat {

    @Autowired
    @Qualifier("PersonServiceImpl")
    private PersonService personService;

    @Test
    public void test() throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring.xml");
        PersonService personServiceImpl = (PersonService) context.getBean("PersonServiceImpl");
        Person person = personServiceImpl.selectPerson(1001);
        System.out.println(person);
        personServiceImpl.updatePerson(new Person(1001, "千寻006", null, null));
        System.out.println("=======================================");
        Person person2 = personServiceImpl.selectPerson(1001);
        System.out.println(person2);
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring.xml");
        PersonService personServiceImpl = (PersonService) context.getBean("PersonServiceImpl");
        Person person1 = personServiceImpl.selectPerson(1001);
        Person person2 = personServiceImpl.selectPerson(1002);

        System.out.println(person1);
        System.out.println("==================================");

       // personServiceImpl.updatePerson(new Person(1001, "千寻007", null, null));

        System.out.println(person2);
    }
}
