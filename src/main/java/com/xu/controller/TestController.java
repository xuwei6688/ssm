package com.xu.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
    @RequestMapping(value = "/method1", method = RequestMethod.GET)
    public String method1() {
        System.out.println("hello");
        return "test";
    }
}
