package com.course.system.controller;


import com.course.system.domain.Test;
import com.course.system.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
public class testController {

    @Resource
    private TestService testService;


    @RequestMapping("/test")
    public List<Test> test(){
        return testService.list();
    }

}
