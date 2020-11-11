package com.course.server.service;

import com.course.server.domain.Teacher;
import com.course.server.domain.TeacherExample;
import com.course.server.dto.PageDto;
import com.course.server.dto.TeacherDto;
import com.course.server.mapper.TeacherMapper;
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
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //对遇到第一个 sql 语句 进行分页
        TeacherExample teacherExample = new TeacherExample();
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);

        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        pageDto.setTotal(pageInfo.getTotal());

        List<TeacherDto> teacherDtoList = new ArrayList<>();
        for (int i = 0,l=teacherList.size(); i < l; i++) {
            Teacher teacher = teacherList.get(i);

            TeacherDto teacherDto = new TeacherDto();
            BeanUtils.copyProperties(teacher,teacherDto);

            teacherDtoList.add(teacherDto);

        }
        pageDto.setList(teacherDtoList);


    }

    /**
     * 列表查询
     */
    public void save(TeacherDto teacherDto) {
//        teacherDto.setId(UuidUtil.getShortUuid());
//        Teacher teacher = new Teacher();
//        BeanUtils.copyProperties(teacherDto,teacher);
//        teacherMapper.insert(teacher);

        Teacher teacher = CopyUtil.copy(teacherDto,Teacher.class);
        if(StringUtils.isEmpty(teacherDto.getId())){
            this.insert(teacher);
        }else{
            this.update(teacher);
        }

    }

    private void insert(Teacher teacher){
        teacher.setId(UuidUtil.getShortUuid());
        teacherMapper.insert(teacher);
    }


    private void update(Teacher teacher){

        teacherMapper.updateByPrimaryKey(teacher);
    }


    public void delete(String id){
        teacherMapper.deleteByPrimaryKey(id);
    }

    public List<TeacherDto> all(){
        TeacherExample teacherExample = new TeacherExample();
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        List<TeacherDto> teacherDtoList = CopyUtil.copyList(teacherList,TeacherDto.class);
        return teacherDtoList;
    }

    /**
     * 查找
     * @param id
     */
    public TeacherDto findById(String id) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        return CopyUtil.copy(teacher, TeacherDto.class);
    }


}
