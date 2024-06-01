package org.example.tlias.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tlias.mapper.EmpMapper;
import org.example.tlias.pojo.PageBean;
import org.example.tlias.pojo.Emp;
import org.example.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        PageBean pageBean = new PageBean();
//        pageBean.setTotal(empMapper.count());
//        Integer start = (page - 1) * pageSize;
//        pageBean.setRows(empMapper.page(start, pageSize));
//        return pageBean;
//    }


    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,
                         LocalDate begin, LocalDate end) {
        //通过pageHelper实现
        PageHelper.startPage(page, pageSize);

        //设置分页参数
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp getById(Integer id) {
        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
