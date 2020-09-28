package com.ly.dao;

import com.ly.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("studentDao")
public class StudentDao {

    public List<Student> getAllStudent(){
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("1", "tom", 18, true));
        list.add(new Student("2", "cat", 28, true));
        list.add(new Student("3", "lxk", 18, true));
        list.add(new Student("4", "cms", 18, true));
        return list;
    }

}
