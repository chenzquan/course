package com.course.server.service;

import com.course.server.domain.roleUser;
import com.course.server.domain.roleUserExample;

import com.course.server.dto.roleUserDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.roleUserMapper;
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
public class roleUserService {

    @Resource
    private roleUserMapper roleUserMapper;

    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //对遇到第一个 sql 语句 进行分页
        roleUserExample roleUserExample = new roleUserExample();
        List<roleUser> roleUserList = roleUserMapper.selectByExample(roleUserExample);

        PageInfo<roleUser> pageInfo = new PageInfo<>(roleUserList);
        pageDto.setTotal(pageInfo.getTotal());

        List<roleUserDto> roleUserDtoList = new ArrayList<>();
        for (int i = 0,l=roleUserList.size(); i < l; i++) {
            roleUser roleUser = roleUserList.get(i);

            roleUserDto roleUserDto = new roleUserDto();
            BeanUtils.copyProperties(roleUser,roleUserDto);

            roleUserDtoList.add(roleUserDto);

        }
        pageDto.setList(roleUserDtoList);


    }

    /**
     * 列表查询
     */
    public void save(roleUserDto roleUserDto) {
//        roleUserDto.setId(UuidUtil.getShortUuid());
//        roleUser roleUser = new roleUser();
//        BeanUtils.copyProperties(roleUserDto,roleUser);
//        roleUserMapper.insert(roleUser);

        roleUser roleUser = CopyUtil.copy(roleUserDto,roleUser.class);
        if(StringUtils.isEmpty(roleUserDto.getId())){
            this.insert(roleUser);
        }else{
            this.update(roleUser);
        }

    }

    private void insert(roleUser roleUser){
        roleUser.setId(UuidUtil.getShortUuid());
        roleUserMapper.insert(roleUser);
    }


    private void update(roleUser roleUser){

        roleUserMapper.updateByPrimaryKey(roleUser);
    }


    public void delete(String id){
        roleUserMapper.deleteByPrimaryKey(id);
    }


}
