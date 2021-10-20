package com.log.manager.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="user_account")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    public User() {}

    public User(String username, String email, String password) {
        this.username=username;
        this.email=email;
        this.password = password;
    }

    //region GETTERS
    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }
    //endregion

    //region SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }
    //endregion

    //region OVERRIDES
    @Override
    public boolean equals(Object o) {
        if (this==o) {
            return true;
        }
        if(!(o instanceof User)) {
            return false;
        }

        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.username, user.username) && Objects.equals(this.email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.email);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", username='" + this.username + '\'' + ", email='" + this.email + '\'' + '}';
    }
    //endregion
}
