package vamk.fi.e2000575.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vamk.fi.e2000575.server.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
