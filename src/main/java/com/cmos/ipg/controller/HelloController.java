package com.cmos.ipg.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by jackl on 2016/4/27.
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
   public String hello(){
        return "hello:"+new Date().getTime();
    }

}
