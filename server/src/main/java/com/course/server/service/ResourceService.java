package com.course.server.service;

import com.course.server.domain.Resource;
import com.course.server.domain.ResourceExample;

import com.course.server.dto.ResourceDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ResourceMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;



@Service
public class ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //对遇到第一个 sql 语句 进行分页
        ResourceExample resourceExample = new ResourceExample();
        List<Resource> resourceList = resourceMapper.selectByExample(resourceExample);

        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);
        pageDto.setTotal(pageInfo.getTotal());

        List<ResourceDto> resourceDtoList = new ArrayList<>();
        for (int i = 0,l=resourceList.size(); i < l; i++) {
            Resource resource = resourceList.get(i);

            ResourceDto resourceDto = new ResourceDto();
            BeanUtils.copyProperties(resource,resourceDto);

            resourceDtoList.add(resourceDto);

        }
        pageDto.setList(resourceDtoList);


    }

    /**
     * 列表查询
     */
    public void save(ResourceDto resourceDto) {
//        resourceDto.setId(UuidUtil.getShortUuid());
//        Resource resource = new Resource();
//        BeanUtils.copyProperties(resourceDto,resource);
//        resourceMapper.insert(resource);

        Resource resource = CopyUtil.copy(resourceDto,Resource.class);
        if(StringUtils.isEmpty(resourceDto.getId())){
            this.insert(resource);
        }else{
            this.update(resource);
        }

    }

    private void insert(Resource resource){
        resource.setId(UuidUtil.getShortUuid());
        resourceMapper.insert(resource);
    }


    private void update(Resource resource){

        resourceMapper.updateByPrimaryKey(resource);
    }


    public void delete(String id){
        resourceMapper.deleteByPrimaryKey(id);
    }


}
