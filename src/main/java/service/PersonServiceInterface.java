package service;

import models.Person;
import java.util.List;

public interface PersonServiceInterface {

    List<Person> getAllPeople();

    Person getPersonById(int id);

    void savePerson(Person person);

    void updatePerson(int id, Person updatedPerson);

    void deletePerson(int id);
}