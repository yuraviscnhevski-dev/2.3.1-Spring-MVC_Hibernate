package dao;

import models.Person;
import java.util.List;

public interface PersonDAOInterface {

    List<Person> index();

    Person show(int id);

    void save(Person person);

    void update(int id, Person updatedPerson);

    void delete(int id);
}