package com.arpico.dojoblog.service;

import com.arpico.dojoblog.dto.AdminDto;
import com.arpico.dojoblog.dto.AuthorDto;
import com.arpico.dojoblog.dto.ResponseDto;
import com.arpico.dojoblog.model.Admin;

import java.util.List;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 9:59 AM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

public interface AdminService {
    void addRoleToAuthor(String username, String roleName);
    Admin getUser(String username);
    List<AdminDto> getUser();
    ResponseDto getAllAuthor();
}

