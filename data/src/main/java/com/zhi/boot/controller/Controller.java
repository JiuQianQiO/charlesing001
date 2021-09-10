package com.zhi.boot.controller;

import com.zhi.boot.bean.Student;
import com.zhi.boot.service.MyStudentService;
import com.zhi.boot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StudentService studentService;

@Autowired
    MyStudentService myStudentService;

    @ResponseBody
    @GetMapping("/sel")
    public List<Student> sel(){
        return studentService.ListStudent();
    }
    @RequestMapping(value = "/stu")
    @ResponseBody
    public  Student stu(@RequestParam("id") Integer id){
        return studentService.getStudent(id);
    }


    @ResponseBody
    @RequestMapping("/byid")
    public Student Byid(@RequestParam("id") Integer id){
      return   studentService.getById(id);
    }

    @GetMapping("sql")
    @ResponseBody
    public  String sql(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from student", Long.class);
        return aLong.toString();
    }

    @ResponseBody
    @GetMapping("/plus")
    public  List<Student> plus(Model model){
        List<Student> list = myStudentService.list();
        /**
         * 通过mybatis -plus 获取的数据，
         * 可以将数据放到 model 里面
         * 通过model 将数据传送到页面，
         * 在页面进行遍历，显示到网页上面
         */
        return list;
    }

}
