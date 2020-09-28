package com.ly.service;

import com.ly.dao.StudentDao;
import com.ly.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("studentService")
public class StudentService {

    @Resource(name = "studentDao")
    private StudentDao studentDao;

    /**
     * @return 获取所有学生
     */
    public List<Student> getAllStudent() {
        return studentDao.getAllStudent();
    }
}
