package vamk.fi.e2000575.server.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course", schema = "e2000575_jsp_final", catalog = "")
public class Course {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "courseID")
    private int courseId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "teacher_name")
    private String teacherName;

    @ManyToMany
    @JoinTable(name = "student_join_course", joinColumns = @JoinColumn(name = "courseID"), inverseJoinColumns = @JoinColumn(name = "studentID"))
    private Set<Student> studentName = new HashSet<>();
}
