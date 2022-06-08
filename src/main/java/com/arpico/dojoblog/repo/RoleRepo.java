package com.arpico.dojoblog.repo;

import com.arpico.dojoblog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 9:57 AM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
