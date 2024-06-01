package org.example.tlias.service;

import org.example.tlias.pojo.Dept;

import java.util.List;

public interface DeptService {
//    查询全部部门数据
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    void edit(Dept dept);
}
