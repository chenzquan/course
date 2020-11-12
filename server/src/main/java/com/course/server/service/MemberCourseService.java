package com.course.server.service;

import com.course.server.domain.MemberCourse;
import com.course.server.domain.MemberCourseExample;

import com.course.server.dto.MemberCourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.MemberCourseMapper;
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
public class MemberCourseService {

    @Resource
    private MemberCourseMapper memberCourseMapper;

    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //对遇到第一个 sql 语句 进行分页
        MemberCourseExample memberCourseExample = new MemberCourseExample();
        List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(memberCourseExample);

        PageInfo<MemberCourse> pageInfo = new PageInfo<>(memberCourseList);
        pageDto.setTotal(pageInfo.getTotal());

        List<MemberCourseDto> memberCourseDtoList = new ArrayList<>();
        for (int i = 0,l=memberCourseList.size(); i < l; i++) {
            MemberCourse memberCourse = memberCourseList.get(i);

            MemberCourseDto memberCourseDto = new MemberCourseDto();
            BeanUtils.copyProperties(memberCourse,memberCourseDto);

            memberCourseDtoList.add(memberCourseDto);

        }
        pageDto.setList(memberCourseDtoList);


    }

    /**
     * 列表查询
     */
    public void save(MemberCourseDto memberCourseDto) {
//        memberCourseDto.setId(UuidUtil.getShortUuid());
//        MemberCourse memberCourse = new MemberCourse();
//        BeanUtils.copyProperties(memberCourseDto,memberCourse);
//        memberCourseMapper.insert(memberCourse);

        MemberCourse memberCourse = CopyUtil.copy(memberCourseDto,MemberCourse.class);
        if(StringUtils.isEmpty(memberCourseDto.getId())){
            this.insert(memberCourse);
        }else{
            this.update(memberCourse);
        }

    }

    private void insert(MemberCourse memberCourse){
         Date now = new Date();
        memberCourse.setId(UuidUtil.getShortUuid());
        memberCourseMapper.insert(memberCourse);
    }


    private void update(MemberCourse memberCourse){
        Date now = new Date();

        memberCourseMapper.updateByPrimaryKey(memberCourse);
    }


    public void delete(String id){
        memberCourseMapper.deleteByPrimaryKey(id);
    }


}
