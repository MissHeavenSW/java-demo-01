package com.demo.controller;


import com.demo.domain.Request;
import com.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/getGrade")
    public Map<String,Object> getGrade(@RequestBody Request request){
        Map<String,Object> returnMap =  demoService.getGrade2(request);
        return returnMap;
    }


}
