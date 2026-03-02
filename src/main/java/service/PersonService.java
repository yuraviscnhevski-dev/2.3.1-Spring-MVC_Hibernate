package service;

import dao.PersonDAOInterface;
import models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService implements PersonServiceInterface {

    private final PersonDAOInterface personDAO;

    @Autowired
    public PersonService(PersonDAOInterface personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public List<Person> getAllPeople() {

        return personDAO.index();
    }

    @Override
    public Person getPersonById(int id) {
        Person person = personDAO.show(id);
        if (person == null) {
            throw new RuntimeException("Person not found with id: " + id);
        }
        return person;
    }

    @Override
    public void savePerson(Person person) {
        validatePerson(person);
        personDAO.save(person);
    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {
        getPersonById(id);
        validatePerson(updatedPerson);
        personDAO.update(id, updatedPerson);
    }

    @Override
    public void deletePerson(int id) {
        getPersonById(id);
        personDAO.delete(id);
    }

    private void validatePerson(Person person) {
        if (person.getName() == null || person.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (person.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be positive");
        }
    }
}