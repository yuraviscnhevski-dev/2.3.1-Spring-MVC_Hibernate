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
        // Здесь можно добавить бизнес-логику, например:
        // - проверка прав доступа
        // - логирование
        // - кэширование
        // - фильтрация
        return personDAO.index();
    }

    @Override
    public Person getPersonById(int id) {
        // Можно добавить проверку существования
        Person person = personDAO.show(id);
        if (person == null) {
            throw new RuntimeException("Person not found with id: " + id);
        }
        return person;
    }

    @Override
    public void savePerson(Person person) {
        // Валидация данных перед сохранением
        validatePerson(person);
        personDAO.save(person);
    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {
        // Проверяем, существует ли запись
        getPersonById(id); // выбросит исключение, если не найдено
        validatePerson(updatedPerson);
        personDAO.update(id, updatedPerson);
    }

    @Override
    public void deletePerson(int id) {
        // Проверяем, существует ли запись
        getPersonById(id); // выбросит исключение, если не найдено
        personDAO.delete(id);
    }

    // Приватный метод для валидации
    private void validatePerson(Person person) {
        if (person.getName() == null || person.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (person.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be positive");
        }
        // Добавьте другие проверки по необходимости
    }
}