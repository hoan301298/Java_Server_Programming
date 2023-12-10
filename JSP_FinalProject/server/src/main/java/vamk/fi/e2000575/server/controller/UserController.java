package vamk.fi.e2000575.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vamk.fi.e2000575.server.auth.AuthenticationRequest;
import vamk.fi.e2000575.server.auth.AuthenticationService;
import vamk.fi.e2000575.server.entity.RegisterRequest;
import vamk.fi.e2000575.server.entity.Student;
import vamk.fi.e2000575.server.repository.UserDataRepository;

@Controller
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserDataRepository userDataRepository;

    @PostMapping("/userPage")
    public String userPage(Model model) {
        model.addAttribute("user_service", true);
        return "register-login-user";
    }

    @GetMapping("/registerForm")
    public String registerForm(Model model) {
        model.addAttribute("register", true);
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register-login-user";
    }

    @PostMapping("/registerForm")
    public String register(
            @ModelAttribute("registerRequest") RegisterRequest request,
            Model model) {
        authenticationService.register(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            model.addAttribute("student", new Student());
            return "add-student";
        } else {
            model.addAttribute("register", true);
            model.addAttribute("registerRequest", new RegisterRequest());
            model.addAttribute("badRequest", true);
            model.addAttribute("message", "Username existed, JWT couldn't be created! <br>Try again!");
            return "register-login-user";
        }
    }

    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        model.addAttribute("login", true);
        model.addAttribute("authenticationRequest", new AuthenticationRequest());
        return "register-login-user";
    }

    @PostMapping("/loginForm")
    public String authenticate(
            @ModelAttribute("authenticationRequest") AuthenticationRequest request,
            Model model) {

        if (userDataRepository.findByUsername(request.getUsername()).isPresent()) {
            // boolean check =
            // SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
            // if (check) {
            return "redirect:/add-student";
        } else {
            model.addAttribute("login", true);
            model.addAttribute("authenticationRequest", new AuthenticationRequest());
            model.addAttribute("badRequest", true);
            model.addAttribute("message", "Username and password incorrect... <br>Try again!");
            return "register-login-user";
        }
    }

    @GetMapping("/add-student")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("message", "");
        return "add-student";
    }

}
