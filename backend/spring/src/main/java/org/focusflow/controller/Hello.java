package org.focusflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/")
@RestController
public class Hello {

    @GetMapping("hello")
    public String hello(){
        return "Test 2";
    }
}
