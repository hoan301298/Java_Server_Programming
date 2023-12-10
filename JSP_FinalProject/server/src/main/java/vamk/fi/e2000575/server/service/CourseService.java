package vamk.fi.e2000575.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vamk.fi.e2000575.server.entity.Course;
import vamk.fi.e2000575.server.entity.CourseWithStudentName;
import vamk.fi.e2000575.server.entity.Student;
import vamk.fi.e2000575.server.repository.CourseRepository;

@Service
@AllArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    List<Course> courses = new ArrayList<>();

    List<CourseWithStudentName> courseDetail = new ArrayList<>();

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<CourseWithStudentName> getCourseById(int id) {
        Course course = courseRepository.getReferenceById(id);
        String studentGetName = "";
        for (Student student : course.getStudentName()) {
            studentGetName = studentGetName + student.getFirstName() + " " + student.getLastName() + "," + " ";
        }
        StringBuilder studentJoined = new StringBuilder(studentGetName);
        studentJoined.deleteCharAt(studentGetName.length() - 2);

        CourseWithStudentName courseWithStudentName = new CourseWithStudentName();
        courseWithStudentName.setCourseId(course.getCourseId());
        courseWithStudentName.setName(course.getName());
        courseWithStudentName.setTeacherName(course.getTeacherName());
        courseWithStudentName.setStudentName(studentJoined.toString());

        courseDetail.add(courseWithStudentName);
        return courseDetail;
    }

    // public String updateCourseById(int id, Course courseChanged) {

    // Course course = courseRepository.getReferenceById(id);
    // if (courseChanged != null) {
    // course.setName(courseChanged.getName());
    // course.setTeacherName(courseChanged.getTeacherName());
    // courseRepository.save(course);
    // return "Updated successfully!";
    // }

    // return "Error found!";
    // }

    // public String deleteCourseById(int id) {
    // Course course = courseRepository.getReferenceById(id);
    // if (course.equals(null)) {
    // courseRepository.delete(course);
    // return "Course deleted!";
    // }
    // return "Course Not Found";
    // }
}
