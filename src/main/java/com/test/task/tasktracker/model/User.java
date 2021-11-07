package com.test.task.tasktracker.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends RepresentationModel<User> {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Must start with a capital letter followed by one or more lowercase letters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Must start with a capital letter followed by one or more lowercase letters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Must be a valid e-mail address")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Task> myTasks;
}
