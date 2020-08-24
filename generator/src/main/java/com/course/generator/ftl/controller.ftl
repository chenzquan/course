package com.course.business.controller.admin;


import com.course.server.domain.${Domain};

import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.${Domain}Service;

import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

    @Resource
    private ${Domain}Service ${Domain}Service;

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);

    public static final String BUSINESS_NAME = "大章";


    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){

        ResponseDto responseDto = new ResponseDto();

        ${Domain}Service.list(pageDto);

        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto){

        // 保存校验


        ResponseDto responseDto = new ResponseDto();
        ${Domain}Service.save(${domain}Dto);
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        ${Domain}Service.delete(id);
//        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

}
