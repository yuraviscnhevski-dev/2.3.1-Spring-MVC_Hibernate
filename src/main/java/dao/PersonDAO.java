package dao;

import models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> index() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class)
                .getResultList();
    }

    public Person show(int id) {
        return entityManager.find(Person.class, id);
    }

    public void save(Person person) {
        entityManager.persist(person);
    }

    public void update(int id, Person updatedPerson) {
        Person person = entityManager.find(Person.class, id);
        person.setName(updatedPerson.getName());
        // Если добавите другие поля, обновляйте их здесь
    }

    public void delete(int id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            entityManager.remove(person);
        }
    }
}