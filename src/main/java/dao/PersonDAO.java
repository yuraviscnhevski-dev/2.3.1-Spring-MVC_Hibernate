package dao;

import models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PersonDAO implements PersonDAOInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> index() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class)
                .getResultList();
    }

    @Override
    public Person show(int id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Override
    public void update(int id, Person updatedPerson) {
        Person person = entityManager.find(Person.class, id);
        person.setName(updatedPerson.getName());
        person.setLastName(updatedPerson.getLastName());
        person.setAge(updatedPerson.getAge());
    }

    @Override
    public void delete(int id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            entityManager.remove(person);
        }
    }
}