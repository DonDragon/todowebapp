package ua.od.hillel.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.od.hillel.todo.dao.TODODao;
import ua.od.hillel.todo.entities.TODOEntry;
import ua.od.hillel.todo.entities.TODOList;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Base controller
 */
@Controller
@RequestMapping("/")
public class TODOListController {


    private static final Logger logger = Logger.getLogger(TODOListController.class);

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
        logger.debug(model);
        model.addAttribute("lists", dao.findTODOLists());
        model.addAttribute("allChecked", allListsChecked());
		return "index";
	}

    /**
     * Delete
     */
    @RequestMapping(value = "/lists/delete", method = RequestMethod.GET)
    public String delete() {
        for (TODOList list : dao.findTODOLists()) {
            if (list.getChecked())
                dao.delete(list.getId());
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/entry/delete", method = RequestMethod.GET)
    public String deleteEntry(@RequestParam("list_id") Long listId,
                              @RequestParam("entry_id") Long entryId) {

        dao.deleteEntry(entryId);

        return "redirect:/lists/" + listId;
    }

    /**
     * Show list
     */
    @RequestMapping(value = "/lists/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable Long id, ModelMap model) {
        TODOList list = dao.load(TODOList.class, id);

        list.setEntries(dao.sortTODOEntry(id));

        model.addAttribute("list", list);

        TODOEntry entry = new TODOEntry();
        entry.setList(list);
        return new ModelAndView("lists/show", "command", entry);
    }

    @RequestMapping(value = "/{entity}/{id}/toggle", method = RequestMethod.GET)
    public String toggleEntry( @PathVariable String entity, @PathVariable Long id) {
        if (entity.equals("entries")) {
            TODOEntry entry = dao.load(TODOEntry.class, id);
            entry.setDone( !entry.getDone() );
            dao.update(entry);
            return "redirect:/lists/" + entry.getList().getId();
        }
        if (entity.equals("lists")) {
            TODOList list = dao.load(TODOList.class, id) ;
            list.setChecked(!list.getChecked());
            dao.update(list);
            return "redirect:/";
        } else
            return "404";
    }

    /**
     * Create List
     */

    @RequestMapping(value = "/lists/addlist", method = RequestMethod.POST)
    public String addList(@ModelAttribute("list") TODOList todoList, BindingResult result) {
        dao.create(todoList);
        return "redirect:/";
    }


    @RequestMapping("/lists/create")
    public ModelAndView showForm() {
        return new ModelAndView("list", "command", new TODOList());
    }


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
        return new ModelAndView("editlist", "command", dao.load(TODOList.class, id));
    }

    @RequestMapping(value = "/editList", method = RequestMethod.POST)
    public String editList(@ModelAttribute("EditList") TODOList todoList, ModelMap map) {
        dao.update(todoList);
        return "redirect:/";
    }

    /**
     * Toggle all lists
     */
    @RequestMapping(value = "/lists/selectall", method = RequestMethod.GET)
    public String toggleAllLists() {
        if (allListsChecked()) {
            checkLists(false);
        } else {
            checkLists(true);
        }
       return "redirect:/";
    }

    private boolean allListsChecked() {
        boolean checked = true;

        if (dao.findTODOLists().size() < 1) {
            return false;
        }

        for (TODOList list : dao.findTODOLists()) {
            if (!list.getChecked()) {
                checked = false;
                break;
            }
        }
        return checked;
    }

    private void checkLists(Boolean b) {
        for (TODOList list : dao.findTODOLists()) {
            list.setChecked(b);
            dao.update(list);
        }

    }

}
