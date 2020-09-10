package com.course.file.controller.admin;


import com.course.server.domain.File;

import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.FileService;

import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/file")
public class FileController {

    @Resource
    private FileService FileService;

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    public static final String BUSINESS_NAME = "文件";


    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){

        ResponseDto responseDto = new ResponseDto();

        FileService.list(pageDto);

        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody FileDto fileDto){

        // 保存校验

        ValidatorUtil.require(fileDto.getPath(),"相对路径");
        ValidatorUtil.length(fileDto.getPath(),"相对路径",1,100);
        ValidatorUtil.length(fileDto.getName(),"文件名",1,100);
        ValidatorUtil.length(fileDto.getSuffix(),"后缀",1,10);
        ValidatorUtil.length(fileDto.getUse(),"用途",1,50);


        ResponseDto responseDto = new ResponseDto();
        FileService.save(fileDto);
        responseDto.setContent(fileDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        FileService.delete(id);
//        responseDto.setContent(fileDto);
        return responseDto;
    }

}
