package com.in28minutos.learn_spring_boot;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    private List<Course> courses = new ArrayList<>();
    private long nextId = 1;

    // Constructor → datos iniciales
    public CourseController() {
        courses.add(new Course(nextId++, "Curso primero", "RubénM"));
        courses.add(new Course(nextId++, "Curso segundo", "Juan"));
    }

    // 🔍 GET → obtener todos los cursos
    @GetMapping("/courses")
    public List<Course> retrieveAllCourses() {
        return courses;
    }

    // 🔍 GET → obtener un curso por ID
    @GetMapping("/courses/{id}")
    public Course retrieveCourseById(@PathVariable long id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null; // luego lo mejoramos
    }

    // ➕ POST → crear un curso
    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        course = new Course(nextId++, course.getName(), course.getAutor());
        courses.add(course);
        return course;
    }

    // ✏️ PUT → actualizar un curso
    @PutMapping("/courses/{id}")
    public Course updateCourse(@PathVariable long id, @RequestBody Course updatedCourse) {
        for (Course course : courses) {
            if (course.getId() == id) {
                courses.remove(course);
                Course newCourse = new Course(id, updatedCourse.getName(), updatedCourse.getAutor());
                courses.add(newCourse);
                return newCourse;
            }
        }
        return null;
    }

    // ❌ DELETE → borrar un curso
    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable long id) {
        courses.removeIf(course -> course.getId() == id);
    }
}