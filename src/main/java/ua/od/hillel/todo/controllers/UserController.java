package ua.od.hillel.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import ua.od.hillel.todo.dao.TODODao;
import ua.od.hillel.todo.entities.User;
import ua.od.hillel.todo.entities.UserRoles;
import ua.od.hillel.todo.model.UploadUser;
import ua.od.hillel.todo.validator.FileValidator;

import javax.validation.Valid;
import java.io.*;

@Controller
public class UserController {

    /**
     * Dao
     */
    @Autowired
    private TODODao dao;

    @Autowired
    FileValidator fileValidator;

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String loadRegisterPage(Model m) {
        m.addAttribute("UploadUser", new UploadUser());
        return "user/register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String submitRegisterForm(@Valid @ModelAttribute("UploadUser") UploadUser uploadUser,
                                     BindingResult result, Model m) {

        if (result.hasErrors()) {
            m.addAttribute("hasError", true);
            return "user/register";
        }

        User user = new User(uploadUser);

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

        // save photo
        InputStream inputStream = null;
        OutputStream outputStream = null;

        MultipartFile file = uploadUser.getFile();
        fileValidator.validate(uploadUser.getFile(), result);

        String fileName = file.getOriginalFilename();

        if (result.hasErrors()) {
            m.addAttribute("errorMessage", "Select image");
            m.addAttribute("UploadUser", uploadUser);
            return "user/register";
        }

        try {
            inputStream = file.getInputStream();

            File newFile = new File("src/main/webapp/images/" + fileName);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

        } catch(IOException ex) {
            ex.printStackTrace();
        }

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
