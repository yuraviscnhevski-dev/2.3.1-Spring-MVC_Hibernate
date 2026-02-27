package controllers;

import dao.PersonDAO;
import models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{show}")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";

    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";

    }

    @GetMapping("/{id}/edit")
    public String edit(@RequestParam("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @RequestParam("id") int id){
        personDAO.update(id, person);
        return "redirect:/people";
    }
}
