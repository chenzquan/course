package com.course.business.controller.admin;


import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.TeacherDto;
import com.course.server.service.TeacherService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {

    @Resource
    private TeacherService TeacherService;

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);

    public static final String BUSINESS_NAME = "讲师";


    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){

        ResponseDto responseDto = new ResponseDto();

        TeacherService.list(pageDto);

        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody TeacherDto teacherDto){

        // 保存校验

        ValidatorUtil.require(teacherDto.getName(),"姓名");
        ValidatorUtil.length(teacherDto.getName(),"姓名",1,50);
        ValidatorUtil.length(teacherDto.getNickname(),"昵称",1,50);
        ValidatorUtil.length(teacherDto.getImage(),"头像",1,100);
        ValidatorUtil.length(teacherDto.getPosition(),"职位",1,50);
        ValidatorUtil.length(teacherDto.getMotto(),"座右铭",1,50);
        ValidatorUtil.length(teacherDto.getIntro(),"简介",1,100);


        ResponseDto responseDto = new ResponseDto();
        TeacherService.save(teacherDto);
        responseDto.setContent(teacherDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        TeacherService.delete(id);
//        responseDto.setContent(teacherDto);
        return responseDto;
    }

}
