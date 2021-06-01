package com.feiyu.web;

import com.alibaba.fastjson.JSON;
import com.feiyu.pojo.Person;
import com.feiyu.service.PersonService;
import com.feiyu.utils.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Autler feiYu
 */
@Controller
public class PersonController{

    /**
     *
     */
    @Autowired
    @Qualifier("PersonServiceImpl")
    private PersonService personService;

    @RequestMapping("/toIndex")
    public ModelAndView toIndex(){
        ModelAndView mv = new ModelAndView("personInfo");
        mv.addObject("msg", "hello yujp666!");
        return mv;
    }

    /**
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/persons")
    public String getPersons() {

        Person person = personService.selectPerson(1001);

        System.out.println("==================================");

        System.out.println(person);
        String jsons = JSON.toJSONString(person);

        personService.updatePerson(new Person(1001, "千寻006", null, null));

        Person person2 = personService.selectPerson(1001);
        System.out.println(person2);
        return jsons;
    }

    @ResponseBody
    @RequestMapping("/persons2")
    public String getPersons2() {

        List<Person> personList = personService.selectPersons();
        List<Person> personList2 = personService.selectPersons();

        System.out.println("==================================");

        System.out.println(personList);
        String jsons = JSON.toJSONString(personList);

        personService.updatePerson(new Person(1001, "千寻006", null, null));

        System.out.println(personList2);
        return jsons;
    }
}
