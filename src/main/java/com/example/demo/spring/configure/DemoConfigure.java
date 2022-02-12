package com.example.demo.spring.configure;

import com.example.demo.spring.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Leo liang
 * @Date 2022/2/12
 */
@Configuration
public class DemoConfigure {

    @Bean("finalPerson")
    @Scope("prototype")
    public Person buildPerson(@Qualifier("myPerson") Person person) {
        person.setName("Leo");
        person.setAge(19);
        person.setDesc("这个人很懒，什么都没留下");
        person.setHigh(172);
        person.setHobby("画画");
        person.setSex("男");
        person.setWeight(140);
        return person;
    }








}
