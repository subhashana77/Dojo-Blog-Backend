package com.arpico.dojoblog.service.impl;

import com.arpico.dojoblog.dto.AdminDto;
import com.arpico.dojoblog.dto.AuthorDto;
import com.arpico.dojoblog.dto.ResponseDto;
import com.arpico.dojoblog.model.Admin;
import com.arpico.dojoblog.model.Author;
import com.arpico.dojoblog.model.Role;
import com.arpico.dojoblog.repo.AdminRepo;
import com.arpico.dojoblog.repo.AuthorRepo;
import com.arpico.dojoblog.repo.RoleRepo;
import com.arpico.dojoblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 10:03 AM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByUsername(username);

        if (admin == null) {
            System.out.println("Admin not found in the database!");
            throw new UsernameNotFoundException("Admin not found in the database!");
        } else {
            System.out.println(username + " found in the database!");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        admin.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(), authorities);
    }

    @Override
    public void addRoleToAuthor(String username, String roleName) {
        System.out.println("Adding " + roleName + "to " + username);
        Admin byUsername = adminRepo.findByUsername(username);
        Role byName = roleRepo.findByName(roleName);
        byUsername.getRoles().add(byName);
    }

    @Override
    public Admin getUser(String username) {
        System.out.println("Fetching " + username);
        return adminRepo.findByUsername(username);
    }

    @Override
    public List<AdminDto> getUser() {
        List<AdminDto> adminList = new ArrayList<>();
        List<Admin> admins = adminRepo.findAll();

        for (Admin admin : admins) {
            AdminDto adminDto = new AdminDto();

            adminDto.setId(admin.getId());
            adminDto.setName(admin.getName());
            adminDto.setUsername(admin.getUsername());
            adminDto.setPassword(admin.getPassword());
            adminDto.setRole(admin.getRoles());

            adminList.add(adminDto);
        }
        return adminList;
    }

    @Override
    public ResponseDto getAllAuthor() {
        try {
            List<Author> allAuthors = authorRepo.findAll();
            List<AuthorDto> authorList = new ArrayList<>();

            if (allAuthors.isEmpty()) {
                return new ResponseDto(
                        false,
                        "No any registered authors!"
                );
            } else {
                for (Author author : allAuthors) {
                    AuthorDto authorDto = new AuthorDto();

                    authorDto.setId(author.getId());
                    authorDto.setName(author.getName());
                    authorDto.setEmail(author.getEmail());
                    authorDto.setTelephone(author.getTelephone());
                    authorDto.setUsername(author.getUsername());
                    authorDto.setPassword(author.getPassword());

                    authorList.add(authorDto);
                }
                return new ResponseDto(
                        true,
                        "All Authors are fetched!",
                        authorList
                );
            }
        } catch (Exception exception) {
            return new ResponseDto(
                    false,
                    "Something went wrong | " + exception
            );
        }
    }
}
