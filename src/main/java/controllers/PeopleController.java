package controllers;

import models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.PersonServiceInterface;  // ← теперь зависит от сервиса

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonServiceInterface personService;  // ← заменили DAO на сервис

    @Autowired
    public PeopleController(PersonServiceInterface personService) {
        this.personService = personService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personService.getAllPeople());  // ← через сервис
        return "people/index";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("person", personService.getPersonById(id));  // ← через сервис
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") Person person) {
        personService.savePerson(person);  // ← через сервис
        return "redirect:/people";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("person", personService.getPersonById(id));  // ← через сервис
        return "people/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id,
                         @ModelAttribute("person") Person person) {
        personService.updatePerson(id, person);  // ← через сервис
        return "redirect:/people";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        personService.deletePerson(id);  // ← через сервис
        return "redirect:/people";
    }
}