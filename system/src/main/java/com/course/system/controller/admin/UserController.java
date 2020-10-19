package com.course.system.controller.admin;


import com.course.server.dto.LoginUserDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.UserDto;
import com.course.server.service.UserService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private UserService UserService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    public static final String BUSINESS_NAME = "用户";


    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){

        ResponseDto responseDto = new ResponseDto();

        UserService.list(pageDto);

        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserDto userDto){

        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));

        // 保存校验

        ValidatorUtil.require(userDto.getLoginName(),"登录名");
        ValidatorUtil.length(userDto.getLoginName(),"登录名",1,50);
        ValidatorUtil.length(userDto.getName(),"呢称",1,50);
        ValidatorUtil.require(userDto.getPassword(),"密码");


        ResponseDto responseDto = new ResponseDto();
        UserService.save (userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }


    @PostMapping("/save-password")
    public ResponseDto savePassword(@RequestBody UserDto userDto){

        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));



        ResponseDto responseDto = new ResponseDto();
        UserService.savePassword (userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = new ResponseDto();
        UserService.delete(id);
//        responseDto.setContent(userDto);
        return responseDto;
    }



    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserDto userDto){

        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto loginUserDto = UserService.login(userDto);
        responseDto.setContent(loginUserDto);
        return responseDto;
    }

}
