package com.study.springcloud.mapper;

import com.study.springcloud.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper : 表示本类是一个 MyBatis 的 Mapper
@Mapper
//@Repository
public interface DepartmentMapper {

    // 获取所有部门信息
    List<Department> getDepartments();

    // 通过id获得部门
    Department getDepartment(Integer id);

}
