package com.zhi.boot;

import com.alibaba.druid.support.http.StatViewServlet;
import com.zhi.boot.bean.Student;
import com.zhi.boot.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootTest
class DataApplicationTests {

    @Autowired
   JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {

        Long aLong = jdbcTemplate.queryForObject("select count(*) from student", Long.class);
        System.out.println(aLong);
        Object o = jdbcTemplate.queryForList("select * from student");
        System.out.println(o);

        System.out.println("==========="+dataSource);
        System.out.println(dataSource.getClass());
    }

    @Autowired
StudentMapper studentMapper;


    @Test
    void test(){
        Student student = studentMapper.selectById(2);
        System.out.println(student);
    }

}
