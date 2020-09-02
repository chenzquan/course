package com.course.server.service;

import com.course.server.domain.CourseContent;
import com.course.server.domain.CourseContentExample;

import com.course.server.dto.CourseContentDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseContentMapper;
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
public class CourseContentService {

    @Resource
    private CourseContentMapper courseContentMapper;

    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //对遇到第一个 sql 语句 进行分页
        CourseContentExample courseContentExample = new CourseContentExample();
        List<CourseContent> courseContentList = courseContentMapper.selectByExample(courseContentExample);

        PageInfo<CourseContent> pageInfo = new PageInfo<>(courseContentList);
        pageDto.setTotal(pageInfo.getTotal());

        List<CourseContentDto> courseContentDtoList = new ArrayList<>();
        for (int i = 0,l=courseContentList.size(); i < l; i++) {
            CourseContent courseContent = courseContentList.get(i);

            CourseContentDto courseContentDto = new CourseContentDto();
            BeanUtils.copyProperties(courseContent,courseContentDto);

            courseContentDtoList.add(courseContentDto);

        }
        pageDto.setList(courseContentDtoList);


    }

    /**
     * 列表查询
     */
    public void save(CourseContentDto courseContentDto) {
//        courseContentDto.setId(UuidUtil.getShortUuid());
//        CourseContent courseContent = new CourseContent();
//        BeanUtils.copyProperties(courseContentDto,courseContent);
//        courseContentMapper.insert(courseContent);

        CourseContent courseContent = CopyUtil.copy(courseContentDto,CourseContent.class);
        if(StringUtils.isEmpty(courseContentDto.getId())){
            this.insert(courseContent);
        }else{
            this.update(courseContent);
        }

    }

    private void insert(CourseContent courseContent){
        courseContent.setId(UuidUtil.getShortUuid());
        courseContentMapper.insert(courseContent);
    }


    private void update(CourseContent courseContent){
        // 表里 有大字段 就会生成 withBLOBs 方法
        courseContentMapper.updateByPrimaryKeyWithBLOBs(courseContent);
    }


    public void delete(String id){
        courseContentMapper.deleteByPrimaryKey(id);
    }


}
