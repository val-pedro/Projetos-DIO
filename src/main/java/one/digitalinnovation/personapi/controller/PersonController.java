package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<PersonDTO> create(@RequestBody @Valid PersonDTO personDTO){
        return new ResponseEntity<PersonDTO>(this.personService.save(personDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll(){
        return new ResponseEntity<List<PersonDTO>>(this.personService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable Long id) throws PersonNotFoundException {
        return new ResponseEntity<PersonDTO>(this.personService.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonDTO> update(@RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return new ResponseEntity<PersonDTO>(this.personService.update(personDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws PersonNotFoundException {
        this.personService.delete(id);
        return new ResponseEntity<String>("Delete success", HttpStatus.OK);
    }
}
