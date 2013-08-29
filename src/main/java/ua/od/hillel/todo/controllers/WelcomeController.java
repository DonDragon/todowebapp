package ua.od.hillel.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.od.hillel.todo.dao.TODODao;
import ua.od.hillel.todo.entities.TODOList;

import javax.persistence.EntityManager;

/**
 * Base controller
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    /**
     * Dao
     */
    @Autowired
    private TODODao dao;


    /**
     * List lists
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(ModelMap model) {
        model.addAttribute("lists", dao.findTODOLists());
		return "index";
	}

    /**
     * Sort lists
     */
    @RequestMapping(value = "/lists/sort", method = RequestMethod.GET)
    public String sort(ModelMap model) {
        model.addAttribute("lists", dao.sortTODOLists());
        return "index";
    }

    /**
     * Delete
     */
    @RequestMapping(value = "/lists/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, ModelMap model) {

        dao.delete(id);
        return "redirect:/";
    }

    /**
     * Show list
     */
    @RequestMapping(value = "/lists/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, ModelMap model) {
        model.addAttribute("list", dao.load(id));
        return "lists/show";

    }

    /**
     * Create List
     * @param model
     * @return
     */
    @RequestMapping(value = "/lists/create", method = RequestMethod.GET)
    public String create(ModelMap model) {

        TODOList list = new TODOList();
        list.setTitle("New TODO List");
        list.setDescription("Cool cool!");
        dao.create(list);

        return "redirect:/";
    }


    /**
     * About
     */
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}

}