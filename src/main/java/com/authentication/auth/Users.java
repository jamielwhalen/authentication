package com.authentication.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

@Getter
@Setter
@ToString
public class Users {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public static Users of(String firstName, String lastName, String email, String password){
        return new Users(null, firstName, lastName, email, password);
    }

    @PersistenceCreator
    private Users(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }



}
