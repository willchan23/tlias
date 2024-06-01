package org.example.tlias.service.impl;

import org.example.tlias.mapper.DeptMapper;
import org.example.tlias.mapper.EmpMapper;
import org.example.tlias.pojo.Dept;
import org.example.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    private EmpMapper empMapper;
    //    查询全部部门数据
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }
    //     根据id删除信息
    @Override
    @Transactional//事务管理
    public void delete(Integer id) {
        //删除部门
        deptMapper.deleteById(id);

        //删除部门下的员工
        empMapper.deleteByDeptId(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    public void edit(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }
}
