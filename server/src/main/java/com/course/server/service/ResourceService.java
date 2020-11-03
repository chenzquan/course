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
import org.springframework.util.CollectionUtils;
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

    /**
     * 按约定将列表转成树
     * 要求：ID要正序排列
     * @return
     */
    public List<ResourceDto> loadTree() {
        ResourceExample example = new ResourceExample();
        example.setOrderByClause("id asc");
        List<Resource> resourceList = resourceMapper.selectByExample(example);
        List<ResourceDto> resourceDtoList = CopyUtil.copyList(resourceList, ResourceDto.class);
        for (int i = resourceDtoList.size() - 1; i >= 0; i--) {
            // 当前要移动的记录
            ResourceDto child = resourceDtoList.get(i);

            // 如果当前节点没有父节点，则不用往下了
            if (StringUtils.isEmpty(child.getParent())) {
                continue;
            }
            // 查找父节点
            for (int j = i - 1; j >= 0; j--) {
                ResourceDto parent = resourceDtoList.get(j);
                if (child.getParent().equals(parent.getId())) {
                    if (CollectionUtils.isEmpty(parent.getChildren())) {
                        parent.setChildren(new ArrayList<>());
                    }
                    // 添加到最前面，否则会变成倒序，因为循环是从后往前循环的
                    parent.getChildren().add(0, child);

                    // 子节点找到父节点后，删除列表中的子节点
                    resourceDtoList.remove(child);
                }
            }
        }
        return resourceDtoList;
    }


}
