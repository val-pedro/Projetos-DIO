package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPerson(@RequestBody Person person){
        return this.personService.createPerson(person);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getAllPerson(){
        return this.personService.getAllPerson();
    }
}
