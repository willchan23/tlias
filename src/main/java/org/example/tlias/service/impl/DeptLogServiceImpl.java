package org.example.tlias.service.impl;

import org.example.tlias.mapper.DeptLogMapper;
import org.example.tlias.pojo.DeptLog;
import org.example.tlias.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;

    //如果不设置propagation，那么就加入了上面的事务，也就是说还是无法插入日志
    //设置了propagation.REQUIRES_NEW之后意味着无论有无总是创造新事物
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
