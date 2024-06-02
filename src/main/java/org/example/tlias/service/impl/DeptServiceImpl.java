package org.example.tlias.service.impl;

import org.example.tlias.mapper.DeptMapper;
import org.example.tlias.mapper.EmpMapper;
import org.example.tlias.pojo.Dept;
import org.example.tlias.pojo.DeptLog;
import org.example.tlias.service.DeptLogService;
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
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    //    查询全部部门数据
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //     根据id删除信息
    @Override
    @Transactional(rollbackFor = Exception.class)
    //@Transactional(rollbackFor = Exception.class)事务管理，默认运行时异常，除了运行时异常外所有异常都回回滚
    public void delete(Integer id) {
        try {
            //删除部门
            deptMapper.deleteById(id);
            int i = 1 / 0;//触发异常
            //删除部门下的员工
            empMapper.deleteByDeptId(id);
        }finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }

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
