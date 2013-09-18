package ua.od.hillel.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.od.hillel.todo.dao.TODODao;
import ua.od.hillel.todo.entities.TODOEntry;
import ua.od.hillel.todo.entities.TODOList;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Base controller
 */
@Controller
@RequestMapping("/")
public class TodosController {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger(TodosController.class);

    /**
     * Dao
     */
    @Autowired
    private TODODao dao;

    /**
     * List lists
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model, @RequestParam(value="sortby", required = false) String sortby) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        ua.od.hillel.todo.entities.User entityUser = dao.findUserByEmail(name);

        List<TODOList> lists = dao.findTODOListsByUser(entityUser.getId());
        boolean allChecked = true;
        for (TODOList l : lists) {
            for (TODOEntry e : l.getEntries()) {
                if (!e.getDone()) {
                    allChecked = false;
                    break;
                }
            }
        }

        model.addAttribute("allChecked", allChecked);
        model.addAttribute("user", entityUser.getUsername());
        model.addAttribute("lists", lists);
		return "index";
	}

    /**
     * Toggle TODOEntry
     */
    @RequestMapping(value = "/{entity}/{id}/toggle", method = RequestMethod.GET)
    public String toggleEntry( @PathVariable String entity, @PathVariable Long id) {
        if (entity.equals("entries")) {
            TODOEntry entry = dao.load(TODOEntry.class, id);
            entry.setDone( !entry.getDone() );
            dao.update(entry);
            return "redirect:/lists/" + entry.getList().getId();
        }

        return "404";
    }

    /**
     * Create List
     */
    @RequestMapping(value = "/lists/addlist", method = RequestMethod.POST)
    public String addList(@ModelAttribute("list") TODOList todoList, BindingResult result) {

        User userData = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userData.getUsername();
        ua.od.hillel.todo.entities.User user = dao.findUserByEmail(name);
        todoList.setUser(user);

        dao.create(todoList);
        return "redirect:/";
    }

    /**
     * Edit List
     */
    @RequestMapping(value = "/editList", method = RequestMethod.POST)
    public String editList(@ModelAttribute("EditList") TODOList todoList, ModelMap map) {

        User userData = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userData.getUsername();
        ua.od.hillel.todo.entities.User user = dao.findUserByEmail(name);
        todoList.setUser(user);

        dao.update(todoList);
        return "redirect:/";
    }

    /**
     * Create List Form
     */
    @RequestMapping("/lists/create")
    public ModelAndView showForm() {
        return new ModelAndView("lists/input", "command", new TODOList());
    }

    /**
     * Delete List
     */
    @RequestMapping(value = "/lists/delete/{id}", method = RequestMethod.GET)
    public String deleteEntry(@PathVariable("id") Long id) {

        dao.deleteById(TODOList.class, id);

        return "redirect:/";
    }


    /**
     * Delete TODOEntry
     */
    @RequestMapping(value = "/entry/delete", method = RequestMethod.GET)
    public String deleteEntry(@RequestParam("list_id") Long listId, @RequestParam("entry_id") Long entryId) {

        dao.deleteById(TODOEntry.class, entryId);

        return "redirect:/lists/" + listId;
    }

    /**
     * Show list
     */
    @RequestMapping(value = "/lists/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, ModelMap model) {

        TODOList list = dao.load(TODOList.class, id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        ua.od.hillel.todo.entities.User user = dao.findUserByEmail(name);

        if ( ! user.getTodoList().contains(list)) {
            return "redirect:/";
        }

        model.addAttribute("list", list);
        model.addAttribute("active", "all");
        return "lists/show";
    }

    /**
     * Show list entries
     */
    @RequestMapping(value = "/lists/{id}/{sort}", method = RequestMethod.GET)
    public String showSortedEntries(@PathVariable Long id, @PathVariable String sort, ModelMap model) {
        TODOList list = dao.load(TODOList.class, id);

        List<TODOEntry> doneEntries = new ArrayList<TODOEntry>();
        List<TODOEntry> undoneEntries = new ArrayList<TODOEntry>();
        String active;

        for (TODOEntry entry : list.getEntries()) {
            if (entry.getDone()) {
                doneEntries.add(entry);
            } else {
                undoneEntries.add(entry);
            }
        }

        if (sort.equals("done")) {
            list.setEntries(doneEntries);
            model.addAttribute("active", "done");
        }
        if (sort.equals("undone")) {
            list.setEntries(undoneEntries);
            model.addAttribute("active", "undone");
        }

        model.addAttribute("list", list);

        return "lists/show";
    }



    /**
     * Create entry
     */
    @RequestMapping(value="/entries/new", method = RequestMethod.POST)
    public String newEntry(@ModelAttribute("entry") TODOEntry entry) {
        dao.create(entry);
        return "redirect:/lists/" + entry.getList().getId();
    }

    /**
     * About
     */
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}

    /**
     * Edit list
     */
    @RequestMapping(value = "/edit/list/{id}", method = RequestMethod.GET)
    public ModelAndView returnList(@PathVariable Long id) {
        return new ModelAndView("lists/edit", "command", dao.load(TODOList.class, id));
    }




}
