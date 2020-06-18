package edu.uph.ii.platformy.models;

import edu.uph.ii.platformy.validators.annotations.UniqueUsername;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by grzesiek on 23.08.2017.
 */
@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 36)
    private String name;
    private String surname;
    @Email
    private String email;

    private Date registrationDate;

    @UniqueUsername
    private String username;
    private String password;
    @Transient//pole nie będzie odwzorowane w db
    private String passwordConfirm;
    @Column(name = "data_urodzin", nullable = false)
    private Date birthDate;
    private boolean enabled = false;

    @AssertTrue
    private boolean isPasswordsEquals(){
        return password == null || passwordConfirm == null || password.equals(passwordConfirm);
    }
/*
    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="acti")
    private TrainingRoom trainingRoom;
*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_record",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "record_id"))
    private Set<Records> records;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String username, String password,String name, String surname, String email, Date birthDate,Date registrationDate){
        this(username,password, name, surname, email, birthDate,registrationDate, false);
    }

    public User(String username,String password, String name, String surname, String email, Date birthDate,Date registrationDate, boolean enabled){
        this.username = username;
        this.enabled = enabled;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.birthDate=birthDate;
        this.password=password;
        this.registrationDate=registrationDate;
    }

}