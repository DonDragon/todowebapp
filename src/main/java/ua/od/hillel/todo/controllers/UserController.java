package ua.od.hillel.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.od.hillel.todo.dao.TODODao;
import ua.od.hillel.todo.entities.User;

/**
 * Created with IntelliJ IDEA.
 * User: altair
 * Date: 07.09.13
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class UserController {

    /**
     * Dao
     */
    @Autowired
    private TODODao dao;

    @RequestMapping(value="register", method= RequestMethod.GET)
    public String loadRegisterPage(Model m) {
        m.addAttribute("User", new User());
        return "user/register";
    }

    @RequestMapping(value="register", method=RequestMethod.POST)
    public String submitRegisterForm(@ModelAttribute("User") User user, Model m) {

        dao.create(user);
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
