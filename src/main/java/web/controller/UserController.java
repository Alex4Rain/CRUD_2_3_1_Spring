package web.controller;

import db.model.User;
import db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public String index(ModelMap model) {
        model.addAttribute("users", service.listUsers());
        model.addAttribute("user", new User());
        return "users/index";
    }

    @PostMapping(value = "/")
    public String add(User user) {
        service.add(user);
        return "redirect:/";
    }

    @PostMapping(value = "/remove")
    public String remove(User user) {
        service.removeUser(user);
        return "redirect:/";
    }
    @PostMapping(value = "/edit")
    public String edit(User user) {
        service.editUser(user);
        return "redirect:/";
    }
}
