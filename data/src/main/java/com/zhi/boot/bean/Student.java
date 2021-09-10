package com.zhi.boot.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;
@Data
@TableName("student")
public class Student {
    private Integer id;
    private String name;
    private Integer age;
}
