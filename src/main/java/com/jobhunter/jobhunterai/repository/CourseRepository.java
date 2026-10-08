package com.jobhunter.jobhunterai.repository;

import com.jobhunter.jobhunterai.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}