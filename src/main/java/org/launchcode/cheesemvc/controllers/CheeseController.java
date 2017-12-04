package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    //static HashMap<String, String> cheeses = new HashMap<>();
    //private static ArrayList<Cheese> cheeses = new ArrayList<>();

    //index
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("title", "My Cheeses");
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String index(Model model, @RequestParam String cheeseNameRemove) {

        model.addAttribute("title", "My Cheeses");
        //display cheeses
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());

        //remove cheeses
        if (cheeseNameRemove!=null) {
            CheeseData.removeCheese(cheeseNameRemove);
        }

        return "cheese/index";
    }

    //add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheese(Model model, @ModelAttribute @Valid Cheese newCheese, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }
        CheeseData.addCheese(newCheese);
        return "redirect:";
    }

    //edit
    @RequestMapping(value = "/edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditCheeseForm(Model model, @PathVariable int cheeseId) {

        model.addAttribute("title", "Edit Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("cheeseInEdit", CheeseData.getById(cheeseId));
        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditCheeseForm(Model model, @RequestParam int cheeseId,
                                        @ModelAttribute @Valid Cheese editCheese, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/edit";
        }
        CheeseData.addCheese(editCheese);
        return "redirect:";
    }

}