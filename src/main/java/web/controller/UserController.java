package web.controller;

import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("users", service.listUsers());
        model.addAttribute("user", new User());
        return "users/index";
    }

    @PostMapping()
    public String add(User user) {
        service.add(user);
        return "redirect:/";
    }

    @DeleteMapping("/remove")
    public String remove(User user) {
        service.removeUser(user);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", service.getUser(id));
        return "users/edit";
    }
    @PatchMapping("/update/{id}")
    public String update(User user) {
        service.editUser(user);
        return "redirect:/";
    }
}
