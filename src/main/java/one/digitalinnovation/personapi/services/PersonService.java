package one.digitalinnovation.personapi.services;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public String createPerson(PersonDTO personDTO) {
        Person personToSave = this.personMapper.toModel(personDTO);

        this.personRepository.save(personToSave);
        return "Person created with success";
    }

    public List<PersonDTO> getAllPerson() {
        List<Person> persons = this.personRepository.findAll();
        return persons.stream()
                .map(this.personMapper::toDTO)
                .collect(Collectors.toList());
    }
}