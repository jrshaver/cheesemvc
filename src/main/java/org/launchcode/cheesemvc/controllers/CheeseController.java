package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.Menu;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.launchcode.cheesemvc.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private MenuDao menuDao;

    //index
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("title", "My Cheeses");
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String index(Model model, @RequestParam int id) {

        try {
            cheeseDao.delete(id);
        }
        catch (Exception ex) { //throws if cheese is in menu
            for (Menu menu : menuDao.findAll()) {
                if (menu.getCheeses().contains(cheeseDao.findOne(id))) {
                    menu.getCheeses().remove(cheeseDao.findOne(id));
                }
            }
        }

        model.addAttribute("title", "My Cheeses");
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/index";
    }

    //add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheese(Model model, @ModelAttribute @Valid Cheese newCheese, Errors errors,
                                   @RequestParam int categoryId) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);

        cheeseDao.save(newCheese);
        return "redirect:";

    }

    //edit
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String displayEditCheeseForm(Model model, @PathVariable int id) {

        model.addAttribute("title", "Edit Cheese");
        model.addAttribute(cheeseDao.findOne(id));
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
    public String processEditCheeseForm(Model model, @PathVariable int id, @ModelAttribute @Valid Cheese editCheese, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/edit";
        }

        else {
            //get cheese id replace with editCheese
            Cheese originalCheese = cheeseDao.findOne(id);
            originalCheese.setCheeseName(editCheese.getCheeseName());
            originalCheese.setDescription(editCheese.getDescription());
            originalCheese.setCheeseRating(editCheese.getCheeseRating());
            originalCheese.setCategory(editCheese.getCategory());
            cheeseDao.save(originalCheese);
            return "redirect:/cheese";
        }
    }

    @RequestMapping(value = "category/{categoryId}", method = RequestMethod.GET)
    public String categories(Model model, @RequestParam int categoryId) {
        model.addAttribute("title", ""); //list all cheeses with category Id
        return "cheese/index";
    }

}