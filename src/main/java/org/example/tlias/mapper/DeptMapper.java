package org.example.tlias.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tlias.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

    //     查询全部部门数据
    @Select("select * from dept")
    List<Dept> list();
    //     根据id删除信息
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);
    @Insert("INSERT into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);
    @Update("update dept set name = #{name}, create_time = #{createTime}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
