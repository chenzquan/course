package com.course.system.controller.admin;


import com.course.server.domain.RoleResource;

import com.course.server.dto.RoleResourceDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.RoleResourceService;

import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/roleResource")
public class RoleResourceController {

    @Resource
    private RoleResourceService RoleResourceService;

    private static final Logger LOG = LoggerFactory.getLogger(RoleResourceController.class);

    public static final String BUSINESS_NAME = "角色资源关联表";


    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){

        ResponseDto responseDto = new ResponseDto();

        RoleResourceService.list(pageDto);

        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody RoleResourceDto roleResourceDto){

        // 保存校验

        ValidatorUtil.require(roleResourceDto.getRoleId(),"角色");
        ValidatorUtil.require(roleResourceDto.getResourceId(),"资源");


        ResponseDto responseDto = new ResponseDto();
        RoleResourceService.save(roleResourceDto);
        responseDto.setContent(roleResourceDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        RoleResourceService.delete(id);
//        responseDto.setContent(roleResourceDto);
        return responseDto;
    }

}
