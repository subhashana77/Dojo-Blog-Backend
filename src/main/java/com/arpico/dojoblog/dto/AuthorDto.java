package com.arpico.dojoblog.dto;

import java.util.List;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 9:44 AM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */
public class AuthorDto {
    private Long id;
    private String name;
    private String telephone;
    private String email;
    private String username;
    private String password;

    public AuthorDto() {
    }

    public AuthorDto(Long id, String name, String telephone, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
