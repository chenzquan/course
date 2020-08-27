package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseExample;

import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
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

import java.util.Date;


@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //对遇到第一个 sql 语句 进行分页
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);

        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotal(pageInfo.getTotal());

        List<CourseDto> courseDtoList = new ArrayList<>();
        for (int i = 0,l=courseList.size(); i < l; i++) {
            Course course = courseList.get(i);

            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(course,courseDto);

            courseDtoList.add(courseDto);

        }
        pageDto.setList(courseDtoList);


    }

    /**
     * 列表查询
     */
    public void save(CourseDto courseDto) {
//        courseDto.setId(UuidUtil.getShortUuid());
//        Course course = new Course();
//        BeanUtils.copyProperties(courseDto,course);
//        courseMapper.insert(course);

        Course course = CopyUtil.copy(courseDto,Course.class);
        if(StringUtils.isEmpty(courseDto.getId())){
            this.insert(course);
        }else{
            this.update(course);
        }

    }

    private void insert(Course course){

        Date now = new Date();
        course.setCreatedAt(now);
        course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }


    private void update(Course course){
        Date now = new Date();
        course.setUpdatedAt(now);

        courseMapper.updateByPrimaryKey(course);
    }


    public void delete(String id){
        courseMapper.deleteByPrimaryKey(id);
    }


}
