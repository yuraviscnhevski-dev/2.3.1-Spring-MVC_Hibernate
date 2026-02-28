package models;

import javax.persistence.*;

@Entity  // ← ЭТО САМОЕ ГЛАВНОЕ!
@Table(name = "users")  // ← указываем имя таблицы
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    // Добавьте остальные поля, если они есть
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private int age;

    public Person() {
    }

    public Person(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}