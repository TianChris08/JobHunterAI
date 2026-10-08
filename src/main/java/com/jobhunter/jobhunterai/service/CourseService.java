package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.Course;
import com.jobhunter.jobhunterai.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id)
                .orElseThrow();
    }

    public Course updateCourse(Long id, Course course) {
        Course existingCourse = getCourse(id);

        existingCourse.setMasterProfile(course.getMasterProfile());
        existingCourse.setName(course.getName());
        existingCourse.setProvider(course.getProvider());
        existingCourse.setDuration(course.getDuration());
        existingCourse.setStartDate(course.getStartDate());
        existingCourse.setCompletionDate(course.getCompletionDate());
        existingCourse.setCredentialUrl(course.getCredentialUrl());

        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}