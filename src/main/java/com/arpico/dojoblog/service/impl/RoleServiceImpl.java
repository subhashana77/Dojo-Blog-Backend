package com.arpico.dojoblog.service.impl;

import com.arpico.dojoblog.dto.RoleDto;
import com.arpico.dojoblog.model.Role;
import com.arpico.dojoblog.repo.RoleRepo;
import com.arpico.dojoblog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 11:09 AM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public List<RoleDto> getRoles() {
        List<RoleDto> roleDTOList = new ArrayList<>();
        List<Role> roles = roleRepo.findAll();

        for (Role role : roles) {
            RoleDto roleDto = new RoleDto();

            roleDto.setId(role.getId());
            roleDto.setName(role.getName());

            roleDTOList.add(roleDto);
        }

        return roleDTOList;
    }
}
