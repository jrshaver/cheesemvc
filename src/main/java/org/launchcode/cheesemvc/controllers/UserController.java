package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "List of Users");
        model.addAttribute("users", UserData.getAllUsers());
        return "user/index";
    }

    @RequestMapping(value = "/{userId}")
    public String userInfo(Model model, @PathVariable int userId) {
        User user = UserData.getUserById(userId);
        model.addAttribute("title", user.getUsername());
        model.addAttribute("user", user);
        return "user/info";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addDisplay(Model model) {
        model.addAttribute("title", "Sign up");
        model.addAttribute(new User());

        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addProcess(Model model, @ModelAttribute @Valid User user, Errors errors) {

        //email and username not valid
        if (errors.hasErrors()) {
            model.addAttribute("title", "Sign up");
            model.addAttribute(user);
            return "user/add";
        }

        else {
            UserData.addUser(user);
            return "redirect:";

        }

    }
}
