package org.example.tlias.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.example.tlias.pojo.Emp;
import org.example.tlias.pojo.PageBean;
import org.example.tlias.pojo.Result;
import org.example.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

//    @GetMapping("/emps")
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize){
//        log.info("分页查询：{},{}",page,pageSize);
//        PageBean pageBean = empService.page(page,pageSize);
//        return Result.success(pageBean);
//    }

    @GetMapping("/emps")
//    参数格式：queryString
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询：{},{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @DeleteMapping("/emps/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping("/emps")
//    参数格式：json
    public Result save(@RequestBody Emp emp){
        log.info("添加员工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/emps/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询信息：{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp){
        log.info("更新员工：{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
