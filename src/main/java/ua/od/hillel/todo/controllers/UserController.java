package ua.od.hillel.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.od.hillel.todo.dao.TODODao;
import ua.od.hillel.todo.entities.User;
import ua.od.hillel.todo.entities.UserRoles;

import javax.validation.Valid;

@Controller
public class UserController {

    /**
     * Dao
     */
    @Autowired
    private TODODao dao;

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String loadRegisterPage(Model m) {
        m.addAttribute("User", new User());
        return "user/register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String submitRegisterForm(@Valid @ModelAttribute("User") User user,
                                     BindingResult result, Model m) {

        if (result.hasErrors()) {
            m.addAttribute("hasError", true);
            return "user/register";
        }

        if (dao.isUsernameExists(user.getUsername())) {
            m.addAttribute("errorMessage", "Username already exists");
            return "user/register";
        }

        user.setEnabled(1);
        dao.create(user);

        User newUser = dao.findUserByName(user.getUsername());

        UserRoles userRoles = new UserRoles();
        userRoles.setUser_id(newUser.getId());
        userRoles.setAuthority("ROLE_USER");
        dao.create(userRoles);

        m.addAttribute("message", "Successfully saved person: " + user.toString());
        return "user/register";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "user/login";
    }

    @RequestMapping(value="/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

        model.addAttribute("error", "true");
        return "user/login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {

        return "user/login";
    }
}
