package com.course.server.mapper;

import com.course.server.domain.roleUser;
import com.course.server.domain.roleUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface roleUserMapper {
    long countByExample(roleUserExample example);

    int deleteByExample(roleUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(roleUser record);

    int insertSelective(roleUser record);

    List<roleUser> selectByExample(roleUserExample example);

    roleUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") roleUser record, @Param("example") roleUserExample example);

    int updateByExample(@Param("record") roleUser record, @Param("example") roleUserExample example);

    int updateByPrimaryKeySelective(roleUser record);

    int updateByPrimaryKey(roleUser record);
}