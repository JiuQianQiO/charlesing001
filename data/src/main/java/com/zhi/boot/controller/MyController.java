package com.zhi.boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MyController {

    @Value("${person.name:Charlesing}")
    public  String name;

    @GetMapping("/")
    public String index(){

        return "hello" + name;
    }
}
