package com.course.server.service;

import com.course.server.domain.File;
import com.course.server.domain.FileExample;
import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.FileMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class FileService {

    @Resource
    private FileMapper fileMapper;

    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //对遇到第一个 sql 语句 进行分页
        FileExample fileExample = new FileExample();
        List<File> fileList = fileMapper.selectByExample(fileExample);

        PageInfo<File> pageInfo = new PageInfo<>(fileList);
        pageDto.setTotal(pageInfo.getTotal());

        List<FileDto> fileDtoList = new ArrayList<>();
        for (int i = 0,l=fileList.size(); i < l; i++) {
            File file = fileList.get(i);

            FileDto fileDto = new FileDto();
            BeanUtils.copyProperties(file,fileDto);

            fileDtoList.add(fileDto);

        }
        pageDto.setList(fileDtoList);


    }

    /**
     * 列表查询
     */
    public void save(FileDto fileDto) {
//        fileDto.setId(UuidUtil.getShortUuid());
//        File file = new File();
//        BeanUtils.copyProperties(fileDto,file);
//        fileMapper.insert(file);

        File file = CopyUtil.copy(fileDto,File.class);
        File fileDb = selectByKey(fileDto.getKey());

        if(fileDb == null){
            this.insert(file);
        }else{
            fileDb.setShardIndex(fileDto.getShardIndex());
            this.update(fileDb);
        }

    }

    private void insert(File file){
         Date now = new Date();
        file.setCreatedAt(now);
        file.setUpdatedAt(now);
        file.setId(UuidUtil.getShortUuid());
        fileMapper.insert(file);
    }


    private void update(File file){
        Date now = new Date();
        file.setUpdatedAt(now);

        fileMapper.updateByPrimaryKey(file);
    }


    public void delete(String id){
        fileMapper.deleteByPrimaryKey(id);
    }


    public  File selectByKey(String key){
        FileExample example = new FileExample();
        example.createCriteria().andKeyEqualTo(key);
        List<File> fileList = fileMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(fileList)){
            return null;
        }else {
            return fileList.get(0);
        }



    }


}
