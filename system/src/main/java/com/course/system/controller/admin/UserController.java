package com.course.system.controller.admin;


import com.alibaba.fastjson.JSON;
import com.course.server.dto.*;
import com.course.server.service.UserService;
import com.course.server.util.UuidUtil;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private UserService UserService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    public static final String BUSINESS_NAME = "用户";

    @Resource
    public RedisTemplate redisTemplate;


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
    public ResponseDto login(@RequestBody UserDto userDto, HttpServletRequest request){

        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        ResponseDto responseDto = new ResponseDto();

        // 根据验证码token去获取缓存中的验证码，和用户输入的验证码是否一致
//         String imageCode = (String) request.getSession().getAttribute(userDto.getImageCodeToken());
        String imageCode = (String) redisTemplate.opsForValue().get(userDto.getImageCodeToken());
        LOG.info("从redis中获取到的验证码：{}", imageCode);
        if (StringUtils.isEmpty(imageCode)) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码已过期");
            LOG.info("用户登录失败，验证码已过期");
            return responseDto;
        }
        if (!imageCode.toLowerCase().equals(userDto.getImageCode().toLowerCase())) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码不对");
            LOG.info("用户登录失败，验证码不对");
            return responseDto;
        } else {
            // 验证通过后，移除验证码
//            request.getSession().removeAttribute(userDto.getImageCodeToken());
            redisTemplate.delete(userDto.getImageCodeToken());
        }



        LoginUserDto loginUserDto = UserService.login(userDto);
        String token = UuidUtil.getShortUuid();
        loginUserDto.setToken(token);

    //    request.getSession().setAttribute(Constants.LOGIN_USER,loginUserDto);  // 把loginUserDto 放到 session 中

        redisTemplate.opsForValue().set(token, JSON.toJSONString(loginUserDto), 300, TimeUnit.SECONDS);
        responseDto.setContent(loginUserDto);
        return responseDto;
    }

    @GetMapping("/logout/{token}")
    public ResponseDto logout(@PathVariable String token){
        ResponseDto responseDto = new ResponseDto();
    //    request.getSession().removeAttribute(Constants.LOGIN_USER);  // 把loginUserDto 放到 session 中

        redisTemplate.delete(token);

        LOG.info("从redis中删除token:{}",token);
        return responseDto;
    }

}
