package one.digitalinnovation.personapi.services;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
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

    public PersonDTO save(PersonDTO personDTO) {
        Person personToSave = this.personMapper.toModel(personDTO);
        return this.personMapper.toDTO(this.personRepository.save(personToSave));
    }

    public List<PersonDTO> findAll() {
        List<Person> persons = this.personRepository.findAll();
        return persons.stream()
                .map(this.personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = this.personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return this.personMapper.toDTO(person);
    }

    public PersonDTO update(PersonDTO personDTO) throws PersonNotFoundException{
        this.findById(personDTO.getId());
        return this.save(personDTO);
    }

    public void delete(Long id) throws PersonNotFoundException {
        PersonDTO personDTO = this.findById(id);
        this.personRepository.delete(this.personMapper.toModel(personDTO));
    }
}