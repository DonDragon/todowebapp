package ua.od.hillel.todo.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.security.provider.MD5;
import ua.od.hillel.todo.dao.TODODao;
import ua.od.hillel.todo.entities.User;
import ua.od.hillel.todo.entities.UserRoles;

import javax.validation.Valid;

/**
 * User Controller
 */
@Controller
public class UserController {

    /**
     * Dao
     */
    @Autowired
    private TODODao dao;

    /**
     * Show Registration
     */
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String showRegister(Model m) {
        m.addAttribute("User", new User());
        return "user/register";
    }

    /**
     * Register / create new user
     */
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String doRegister(@Valid @ModelAttribute("User") User user,
                                     BindingResult result, Model m) {

        if (result.hasErrors()) {
            m.addAttribute("hasError", true);
            return "user/register";
        }

        if (dao.isEmailExists(user.getEmail())) {
            m.addAttribute("errorMessage", "Email already exists");
            return "user/register";
        }
        user.setPassword(
           DigestUtils.md5Hex( user.getPassword() )
        );
        user.setEnabled(1);

        dao.create(user);

        UserRoles userRoles = new UserRoles();
        userRoles.setUser_id(user.getId());
        userRoles.setAuthority("ROLE_USER");
        dao.create(userRoles);

        m.addAttribute("message", "Successfully saved person: " + user.toString());
        return "user/register_success";
    }

    /**
     * Show login
     */
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "user/login";
    }

    /**
     * Login failed
     */
    @RequestMapping(value="/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "user/login";
    }

}
