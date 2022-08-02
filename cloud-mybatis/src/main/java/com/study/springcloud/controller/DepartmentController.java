package com.study.springcloud.controller;

import com.study.springcloud.mapper.DepartmentMapper;
import com.study.springcloud.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;

    // 查询全部部门
    @GetMapping("/getDepartments")
    public List<Department> getDepartments(){
        return departmentMapper.getDepartments();
    }

    // 查询全部部门
    @GetMapping("/getDepartment/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDepartment(id);
    }

    // 测试事务注解
    @GetMapping("/addDepartments")
    @Transactional(noRollbackFor = ArithmeticException.class)
    public String addDepartments() {

        Department dept1 = new Department(null, "A");
        int id = departmentMapper.addDepartment(dept1);
        System.out.println("Added dept, ID: " + dept1.getId());

//        int test = 1 / 0;

        Department dept2 = new Department(null, "B");
        id = departmentMapper.addDepartment(dept2);
        System.out.println("Added dept, ID: " + dept2.getId());

        return "OK";
    }

}
