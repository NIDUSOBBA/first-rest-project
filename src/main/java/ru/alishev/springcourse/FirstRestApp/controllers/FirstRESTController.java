package ru.alishev.springcourse.FirstRestApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FirstRESTController  {

    @GetMapping
    public String sayHello() {
        return "Hello World!";
    }
}
