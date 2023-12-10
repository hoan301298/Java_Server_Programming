package vamk.fi.e2000575.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vamk.fi.e2000575.server.entity.Student;
import vamk.fi.e2000575.server.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentById(int id) {
        List<Student> students = new ArrayList<>();
        students.add(studentRepository.getReferenceById(id));
        return students;
    }

    public boolean createStudent(Student studentInserted) {
        String email = studentInserted.getEmail();
        for (Student student : getAllStudents()) {
            if (student.getEmail().equals(email)) {
                return false;
            }
        }
        studentRepository.save(studentInserted);
        return true;
    }

    // public String updateStudentById(int id, Student studentChanged) {
    // try {
    // student = getStudentById(id);
    // if (student != null && studentChanged != null) {
    // student.setFirstName(studentChanged.getFirstName());
    // student.setFirstName(studentChanged.getLastName());
    // student.setEmail(studentChanged.getEmail());
    // studentRepository.save(student);
    // return "Updated successfully!";
    // }

    // return "Error found!";

    // } catch (Exception e) {
    // return e.getMessage();
    // }
    // }

    // public String deleteStudentById(int id) {
    // student = getStudentById(id);
    // if (student != null) {
    // studentRepository.delete(student);
    // return "Student deleted!";
    // }
    // return "Student Not Found";
    // }
}