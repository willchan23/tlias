package org.example.tlias.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.tlias.pojo.Emp;


import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    @Select("select count(*) from emp")
//    public Long count();
//
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start, Integer pageSize);

    // 使用pageHelper插件，不需要考虑分页，插件帮我们实现
//    @Select("select * from emp")
    public List<Emp> list(String name, Short gender,
                          LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer id);
    void save(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    void update(Emp emp);
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}
