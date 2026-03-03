package service;

import dao.PersonDAOInterface;
import models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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
        return personDAO.show(id);
    }


    @Override
    @Transactional
    public void savePerson(Person person) {
        personDAO.save(person);
    }

    @Override
    @Transactional
    public void updatePerson(int id, Person updatedPerson) {

        if (updatedPerson.getName() == null || updatedPerson.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        personDAO.update(id, updatedPerson);
    }

    @Override
    @Transactional
    public void deletePerson(int id) {
        personDAO.delete(id);
    }
}