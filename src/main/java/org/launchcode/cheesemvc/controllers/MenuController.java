package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Menu;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.launchcode.cheesemvc.models.data.MenuDao;
import org.launchcode.cheesemvc.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private MenuDao menuDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDao.findAll());
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMenu(Model model) {
        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMenu(Model model, @ModelAttribute @Valid Menu newMenu, Errors errors ) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDao.save(newMenu);
        return "redirect:view/" + newMenu.getId();
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int id) {
        model.addAttribute("title", menuDao.findOne(id).getName());
        model.addAttribute("menu", menuDao.findOne(id));
        return "menu/view";
    }

    @RequestMapping(value = "add-item/{id}", method = RequestMethod.GET)
    public String displayAddMenuItem(Model model, @PathVariable int id) {
        model.addAttribute("title", "Add Item to Menu: " + menuDao.findOne(id).getName());
        model.addAttribute("menu", menuDao.findOne(id));
        AddMenuItemForm addMenuItemForm = new AddMenuItemForm(menuDao.findOne(id), cheeseDao.findAll());
        model.addAttribute("form", addMenuItemForm);
        return "menu/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String processAddMenuItem(Model model, @RequestParam int menuId, @RequestParam int cheeseId) {
        Menu theMenu = menuDao.findOne(menuId);
        theMenu.addItem(cheeseDao.findOne(cheeseId));
        menuDao.save(theMenu);
        return "redirect:/menu/view/" + theMenu.getId();
    }
}
