package com.course.server.service;

import com.course.server.domain.User;
import com.course.server.domain.UserExample;

import com.course.server.dto.UserDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.UserMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //对遇到第一个 sql 语句 进行分页
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);

        PageInfo<User> pageInfo = new PageInfo<>(userList);
        pageDto.setTotal(pageInfo.getTotal());

        List<UserDto> userDtoList = new ArrayList<>();
        for (int i = 0,l=userList.size(); i < l; i++) {
            User user = userList.get(i);

            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user,userDto);

            userDtoList.add(userDto);

        }
        pageDto.setList(userDtoList);


    }

    /**
     * 列表查询
     */
    public void save(UserDto userDto) {
//        userDto.setId(UuidUtil.getShortUuid());
//        User user = new User();
//        BeanUtils.copyProperties(userDto,user);
//        userMapper.insert(user);

        User user = CopyUtil.copy(userDto,User.class);
        if(StringUtils.isEmpty(userDto.getId())){
            this.insert(user);
        }else{
            this.update(user);
        }

    }

    private void insert(User user){
        user.setId(UuidUtil.getShortUuid());
        userMapper.insert(user);
    }


    private void update(User user){

        userMapper.updateByPrimaryKey(user);
    }


    public void delete(String id){
        userMapper.deleteByPrimaryKey(id);
    }


}
