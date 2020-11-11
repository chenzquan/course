package com.course.business.controller.admin;


import com.course.server.dto.MemberDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.MemberService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/member")
public class MemberController {

    @Resource
    private MemberService MemberService;

    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);

    public static final String BUSINESS_NAME = "会员";


    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){

        ResponseDto responseDto = new ResponseDto();

        MemberService.list(pageDto);

        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody MemberDto memberDto){

        // 保存校验

        ValidatorUtil.length(memberDto.getMobile(),"手机号",1,11);
        ValidatorUtil.require(memberDto.getPassword(),"密码");
        ValidatorUtil.length(memberDto.getName(),"呢称",1,50);
        ValidatorUtil.length(memberDto.getPhoto(),"头像url",1,200);


        ResponseDto responseDto = new ResponseDto();
        MemberService.save(memberDto);
        responseDto.setContent(memberDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        MemberService.delete(id);
//        responseDto.setContent(memberDto);
        return responseDto;
    }

}
