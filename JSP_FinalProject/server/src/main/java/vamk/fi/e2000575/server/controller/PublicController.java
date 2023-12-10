package vamk.fi.e2000575.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vamk.fi.e2000575.server.service.CourseService;
import vamk.fi.e2000575.server.service.StudentService;

// @RestController
@Controller
// @RequestMapping("/student")
public class PublicController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/student")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("StudentSuccess", true);
        return "view-list";
    }

    @GetMapping("/student/{id}")
    public String getStudentById(@PathVariable int id, Model model) {
        model.addAttribute("students", studentService.getStudentById(id));
        model.addAttribute("StudentSuccess", true);
        return "view-list";
    }

    @GetMapping("/course")
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("CoursesSuccess", true);
        return "view-list";
    }

    @GetMapping("/course/{id}")
    public String getCourseById(@PathVariable int id, Model model) {
        model.addAttribute("courses", courseService.getCourseById(id));
        model.addAttribute("CoursesSuccess", true);
        model.addAttribute("CourseSuccess", true);
        return "view-list";
    }
}
