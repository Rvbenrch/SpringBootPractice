package com.in28minutos.learn_spring_boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {


    @RequestMapping("/courses")
    public List<Course> retieveAllCourses(){
        return Arrays.asList(
                new Course(1,"Curso primero", "Creator: RubénM")

        );

    }
}
