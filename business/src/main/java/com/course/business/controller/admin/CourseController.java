package com.course.business.controller.admin;


import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CourseService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/course")
public class CourseController {

    @Resource
    private CourseService CourseService;

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);

    public static final String BUSINESS_NAME = "课程表";


    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){

        ResponseDto responseDto = new ResponseDto();

        CourseService.list(pageDto);

        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto){

        // 保存校验

        ValidatorUtil.require(courseDto.getName(),"名称");
        ValidatorUtil.length(courseDto.getName(),"名称",1,50);
        ValidatorUtil.length(courseDto.getSummary(),"概述",1,2000);
        ValidatorUtil.length(courseDto.getImage(),"封面",1,100);


        ResponseDto responseDto = new ResponseDto();
        CourseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        CourseService.delete(id);
//        responseDto.setContent(courseDto);
        return responseDto;
    }

}
