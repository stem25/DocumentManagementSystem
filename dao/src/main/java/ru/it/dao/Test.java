package ru.it.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class Test {

    public String sayHello(String name){
        return  "Hellloooo:" + name;
    }
}
