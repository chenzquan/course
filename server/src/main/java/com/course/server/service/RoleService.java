package com.course.server.service;

import com.course.server.domain.Role;
import com.course.server.domain.RoleExample;

import com.course.server.dto.RoleDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.RoleMapper;
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
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //对遇到第一个 sql 语句 进行分页
        RoleExample roleExample = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(roleExample);

        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        pageDto.setTotal(pageInfo.getTotal());

        List<RoleDto> roleDtoList = new ArrayList<>();
        for (int i = 0,l=roleList.size(); i < l; i++) {
            Role role = roleList.get(i);

            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role,roleDto);

            roleDtoList.add(roleDto);

        }
        pageDto.setList(roleDtoList);


    }

    /**
     * 列表查询
     */
    public void save(RoleDto roleDto) {
//        roleDto.setId(UuidUtil.getShortUuid());
//        Role role = new Role();
//        BeanUtils.copyProperties(roleDto,role);
//        roleMapper.insert(role);

        Role role = CopyUtil.copy(roleDto,Role.class);
        if(StringUtils.isEmpty(roleDto.getId())){
            this.insert(role);
        }else{
            this.update(role);
        }

    }

    private void insert(Role role){
        role.setId(UuidUtil.getShortUuid());
        roleMapper.insert(role);
    }


    private void update(Role role){

        roleMapper.updateByPrimaryKey(role);
    }


    public void delete(String id){
        roleMapper.deleteByPrimaryKey(id);
    }


}
