package vamk.fi.e2000575.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseWithStudentName {

    private int courseId;
    private String name;
    private String teacherName;
    private String studentName;
}
