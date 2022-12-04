package jm.task.core.jdbc.model;

import com.sun.istack.NotNull;
import jm.task.core.jdbc.service.UserServiceImpl;

import javax.persistence.*;

@Entity
@Table (name = "Users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "uId", nullable = false, unique = true)
    private Long id;

    @Column(name = "uName", length = 45)
    @NotNull
    private String name;

    @Column (name = "uLastName", length = 45)
    @NotNull
    private String lastName;

    @Column (name = "uAge")
    @NotNull
    private Byte age;

    public User() {
    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName + " " + this.age;
    }
}
