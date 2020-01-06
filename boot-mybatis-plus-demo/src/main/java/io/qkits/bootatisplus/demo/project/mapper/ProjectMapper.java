package io.qkits.bootatisplus.demo.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import io.qkits.bootatisplus.demo.project.entity.Project;

/**
 * @author: patrick on 2020/1/4
 * @Description:
 */
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

}
