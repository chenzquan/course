package com.course.server.service;

import com.course.server.domain.${Domain};
import com.course.server.domain.${Domain}Example;

import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.${Domain}Mapper;
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

<#list typeSet as type>
    <#if type=='Date'>
import java.util.Date;
    </#if>
</#list>


@Service
public class ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //对遇到第一个 sql 语句 进行分页
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        <#list fieldList as field>
            <#if field.nameHump=='sort'>
        ${domain}Example.setOrderByClause("sort asc");
            </#if>
        </#list>
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);

        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDto.setTotal(pageInfo.getTotal());

        List<${Domain}Dto> ${domain}DtoList = new ArrayList<>();
        for (int i = 0,l=${domain}List.size(); i < l; i++) {
            ${Domain} ${domain} = ${domain}List.get(i);

            ${Domain}Dto ${domain}Dto = new ${Domain}Dto();
            BeanUtils.copyProperties(${domain},${domain}Dto);

            ${domain}DtoList.add(${domain}Dto);

        }
        pageDto.setList(${domain}DtoList);


    }

    /**
     * 列表查询
     */
    public void save(${Domain}Dto ${domain}Dto) {
//        ${domain}Dto.setId(UuidUtil.getShortUuid());
//        ${Domain} ${domain} = new ${Domain}();
//        BeanUtils.copyProperties(${domain}Dto,${domain});
//        ${domain}Mapper.insert(${domain});

        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto,${Domain}.class);
        if(StringUtils.isEmpty(${domain}Dto.getId())){
            this.insert(${domain});
        }else{
            this.update(${domain});
        }

    }

    private void insert(${Domain} ${domain}){
    <#list typeSet as type>
        <#if type=='Date'>
         Date now = new Date();
        </#if>
    </#list>
        <#list fieldList as field>
            <#if field.nameHump=='createdAt'>
        ${domain}.setCreatedAt(now);
            </#if>
            <#if field.nameHump=='updatedAt'>
        ${domain}.setUpdatedAt(now);
            </#if>
        </#list>
        ${domain}.setId(UuidUtil.getShortUuid());
        ${domain}Mapper.insert(${domain});
    }


    private void update(${Domain} ${domain}){
    <#list typeSet as type>
        <#if type=='Date'>
        Date now = new Date();
        </#if>
    </#list>
    <#list fieldList as field>
        <#if field.nameHump=='updatedAt'>
        ${domain}.setUpdatedAt(now);
        </#if>
    </#list>

        ${domain}Mapper.updateByPrimaryKey(${domain});
    }


    public void delete(String id){
        ${domain}Mapper.deleteByPrimaryKey(id);
    }


}
