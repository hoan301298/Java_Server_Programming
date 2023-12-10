package vamk.fi.e2000575.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student", schema = "e2000575_jsp_final")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentID")
    private int studentId;

    @Basic
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Basic
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Basic
    @Column(name = "email", length = 255)
    private String email;

}
