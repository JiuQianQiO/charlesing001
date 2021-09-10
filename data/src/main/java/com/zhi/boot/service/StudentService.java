package com.zhi.boot.service;

import com.zhi.boot.bean.Student;
import com.zhi.boot.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    public Student getStudent(Integer id){
        return  studentMapper.getStudent(id);
    }

    public List<Student> ListStudent(){
        return studentMapper.studentList();
    }

    public Student getById(Integer id){
        return studentMapper.getById(id);
    }
}
