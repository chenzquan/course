package com.course.business.controller.admin;


import com.course.server.domain.Section;

import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.SectionService;

import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/section")
public class SectionController {

    @Resource
    private SectionService SectionService;

    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);

    public static final String BUSINESS_NAME = "小节";


    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){

        ResponseDto responseDto = new ResponseDto();

        SectionService.list(pageDto);

        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody SectionDto sectionDto){

        // 保存校验


        ResponseDto responseDto = new ResponseDto();
        SectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        SectionService.delete(id);
//        responseDto.setContent(sectionDto);
        return responseDto;
    }

}
