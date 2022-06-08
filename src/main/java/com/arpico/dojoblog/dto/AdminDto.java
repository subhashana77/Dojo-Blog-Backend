package com.arpico.dojoblog.dto;

import java.util.List;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 9:42 AM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */
public class AdminDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private List role;

    public AdminDto() {
    }

    public AdminDto(Long id, String name, String username, String password, List role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
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

    public List getRole() {
        return role;
    }

    public void setRole(List role) {
        this.role = role;
    }
}
