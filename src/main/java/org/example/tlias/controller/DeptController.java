package org.example.tlias.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tlias.pojo.Dept;
import org.example.tlias.pojo.Result;
import org.example.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {
//    @RequestMapping(value = "/depts",method = RequestMethod.GET) 也可以
    @GetMapping("/depts")
    public Result list(){
        log.info("查询部门全部数据");

        //调用service查询部门数据
        List<Dept> deptList =  deptService.list();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门{}",id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    @PutMapping("/depts")
    public Result edit(@RequestBody Dept dept){
        log.info("编辑部门：{}",dept);
        deptService.edit(dept);
        return Result.success();
    }

    @Autowired
    private DeptService deptService;
}
//@RestController 会将返回result结果处理成json格式的数据
