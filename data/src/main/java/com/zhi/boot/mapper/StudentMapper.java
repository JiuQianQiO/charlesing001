package com.zhi.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhi.boot.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    public List<Student> studentList();
    public Student getStudent(int id);
    @Select("select * from student where id = #{id}")
    public Student getById(int id);
}
