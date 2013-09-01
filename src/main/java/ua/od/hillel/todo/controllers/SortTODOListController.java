package ua.od.hillel.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: altair
 * Date: 31.08.13
 * Time: 20:03
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SortTODOListController {

    @Autowired
    ua.od.hillel.todo.dao.TODODao dao;
    /**
     * Sort lists
     */
    @RequestMapping(value = "/lists/sort/{sortBy}", method = RequestMethod.GET)
    public String sort(@PathVariable String sortBy,  ModelMap model) {
        model.addAttribute("lists", dao.sortTODOLists(sortBy) );
        return "index";
    }
}
