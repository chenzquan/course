package com.course.business.controller.admin;


import com.course.server.dto.MemberCourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.MemberCourseService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController("webMemberCourseController")
@RequestMapping("/admin/memberCourse")
public class MemberCourseController {

    @Resource
    private MemberCourseService MemberCourseService;

    private static final Logger LOG = LoggerFactory.getLogger(MemberCourseController.class);

    public static final String BUSINESS_NAME = "会员课程报名";


    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){

        ResponseDto responseDto = new ResponseDto();

        MemberCourseService.list(pageDto);

        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody MemberCourseDto memberCourseDto){

        // 保存校验

        ValidatorUtil.require(memberCourseDto.getMemberId(),"会员id");
        ValidatorUtil.require(memberCourseDto.getCourseId(),"课程id");
        ValidatorUtil.require(memberCourseDto.getAt(),"报名时间");


        ResponseDto responseDto = new ResponseDto();
        MemberCourseService.save(memberCourseDto);
        responseDto.setContent(memberCourseDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        MemberCourseService.delete(id);
//        responseDto.setContent(memberCourseDto);
        return responseDto;
    }

}
