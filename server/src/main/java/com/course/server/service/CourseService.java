package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseContent;
import com.course.server.domain.CourseExample;
import com.course.server.dto.*;
import com.course.server.enums.CourseStatusEnum;
import com.course.server.mapper.CourseContentMapper;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.my.MyCourseMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CourseService {

    private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private MyCourseMapper myCourseMapper;

    @Resource
    private CourseCategoryService courseCategoryService;

    @Resource
    private CourseContentMapper courseContentMapper;


    @Resource
    private ChapterService chapterService;



    @Resource
    private TeacherService teacherService;

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
    @Transactional
    public void save(CourseDto courseDto) {
        Course course = CopyUtil.copy(courseDto,Course.class);
        if(StringUtils.isEmpty(courseDto.getId())){
            this.insert(course);
        }else{
            this.update(course);
        }
        courseCategoryService.saveBatch(course.getId(),courseDto.getCategorys());

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

    public void updateTime(String courseId){
        LOG.info("更新课程时长:{}",courseId);
        myCourseMapper.updateTime(courseId);
    }

    /**
     * 查找课程内容
     * @param id
     * @return
     */
    public CourseContentDto findContent(String id){
        CourseContent content = courseContentMapper.selectByPrimaryKey(id);
        if(content == null){
            return null;
        }

        return CopyUtil.copy(content,CourseContentDto.class);
    }


    /**
     * 保存课程内容，包含新增和修改
     * @param courseContentDto
     * @return
     */
    public int saveContent(CourseContentDto courseContentDto){
        CourseContent content = CopyUtil.copy(courseContentDto,CourseContent.class);
        int i = courseContentMapper.updateByPrimaryKeyWithBLOBs(content);
        if(i == 0){
            i = courseContentMapper.insert(content);
        }
        return i;
    }


    @Transactional
    public void sort(SortDto sortDto) {

        //修改当前记录的排序值
        myCourseMapper.updateSort(sortDto);

        //
        if(sortDto.getNewSort() > sortDto.getOldSort()){
            myCourseMapper.moveSortsForward(sortDto);
        }


        if(sortDto.getNewSort() < sortDto.getOldSort()){
            myCourseMapper.moveSortsBackward(sortDto);
        }




    }

    /**
     * 新课列表查询，只查询已发布的，按创建日期倒序
     */
    public List<CourseDto> listNew(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andStatusEqualTo(CourseStatusEnum.PUBLISH.getCode());
        courseExample.setOrderByClause("created_at desc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        return CopyUtil.copyList(courseList, CourseDto.class);
    }


    /**
     * 查找某一课程，供web模块用，只能查已发布的
     * @param id
     * @return
     */
    public CourseDto findCourse(String id) {
        Course course = courseMapper.selectByPrimaryKey(id);
        if (course == null || !CourseStatusEnum.PUBLISH.getCode().equals(course.getStatus())) {
            return null;
        }
        CourseDto courseDto = CopyUtil.copy(course, CourseDto.class);

        // 查询内容
        CourseContent content = courseContentMapper.selectByPrimaryKey(id);
        if (content != null) {
            courseDto.setContent(content.getContent());
        }

        // 查找讲师信息
//        TeacherDto teacherDto = teacherService.findById(courseDto.getTeacherId());
//        courseDto.setTeacher(teacherDto);
//
//        // 查找章信息
//        List<ChapterDto> chapterDtoList = chapterService.listByCourse(id);
//        courseDto.setChapters(chapterDtoList);
//
//        // 查找节信息
//        List<SectionDto> sectionDtoList = sectionService.listByCourse(id);
//        courseDto.setSections(sectionDtoList);

        return courseDto;
    }
}
