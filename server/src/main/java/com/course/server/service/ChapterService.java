package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
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
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //对遇到第一个 sql 语句 进行分页
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);

        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());

        List<ChapterDto> chapterDtoList = new ArrayList<>();
        for (int i = 0,l=chapterList.size(); i < l; i++) {
            Chapter chapter = chapterList.get(i);

            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);

            chapterDtoList.add(chapterDto);

        }
        pageDto.setList(chapterDtoList);
//        return pageDto;

    }

    /**
     * 列表查询
     */
    public void save(ChapterDto chapterDto) {
//        chapterDto.setId(UuidUtil.getShortUuid());
//        Chapter chapter = new Chapter();
//        BeanUtils.copyProperties(chapterDto,chapter);
//        chapterMapper.insert(chapter);

        Chapter chapter = CopyUtil.copy(chapterDto,Chapter.class);
        if(StringUtils.isEmpty(chapterDto.getId())){
            this.insert(chapter);
        }else{
            this.update(chapter);
        }

    }

    private void insert(Chapter chapter){
        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);
    }


    private void update(Chapter chapter){
        chapterMapper.updateByPrimaryKey(chapter);
    }


    public void delete(String id){
        chapterMapper.deleteByPrimaryKey(id);
    }


}