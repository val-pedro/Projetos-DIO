package one.digitalinnovation.personapi.services;

import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public String createPerson(Person person){
        this.personRepository.save(person);
        return "Person created with success";
    }

    public List<Person> getAllPerson(){
        return this.personRepository.findAll();
    }
}