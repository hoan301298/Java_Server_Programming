package vamk.fi.e2000575.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import lombok.RequiredArgsConstructor;
import vamk.fi.e2000575.server.auth.AuthenticationRequest;
import vamk.fi.e2000575.server.entity.Student;
import vamk.fi.e2000575.server.service.CourseService;
import vamk.fi.e2000575.server.service.StudentService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/add-student")
    public RedirectView addStudent(
            @ModelAttribute("student") Student student,
            RedirectAttributes redirectAttributes) {

        boolean check = studentService.createStudent(student);
        String result;
        final RedirectView rv = new RedirectView("/auth/add-student", true);
        if (check) {
            result = student.getFirstName() + " " + student.getLastName() + " has been successfully added!";
        } else {
            result = "Error, this email has been used!<br>Try again...";
        }
        redirectAttributes.addFlashAttribute("student", new Student());
        redirectAttributes.addFlashAttribute("message", result);
        return rv;
    }

}
