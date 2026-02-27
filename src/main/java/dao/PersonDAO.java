package dao;

import models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        // ИСПРАВЛЕНО: правильный порядок параметров (String name, int id)
        people.add(new Person("Tom", ++PEOPLE_COUNT));
        people.add(new Person("Bob", ++PEOPLE_COUNT));
        people.add(new Person("Max", ++PEOPLE_COUNT));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdate = show(id);
        if (personToBeUpdate != null) {
            personToBeUpdate.setName(updatePerson.getName());
        }
    }

    public void delete(int id) {  // ИСПРАВЛЕНО: int вместо in
        people.removeIf(p -> p.getId() == id);
    }
}