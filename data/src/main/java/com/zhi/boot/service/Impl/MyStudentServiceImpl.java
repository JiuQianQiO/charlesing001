package com.zhi.boot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhi.boot.bean.Student;
import com.zhi.boot.mapper.StudentMapper;
import com.zhi.boot.service.MyStudentService;
import org.springframework.stereotype.Service;

@Service
public class MyStudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements MyStudentService {
}
