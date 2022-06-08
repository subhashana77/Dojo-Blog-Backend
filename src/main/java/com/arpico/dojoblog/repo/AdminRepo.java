package com.arpico.dojoblog.repo;

import com.arpico.dojoblog.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 9:56 AM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
