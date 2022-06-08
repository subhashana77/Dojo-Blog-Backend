package com.arpico.dojoblog.repo;

import com.arpico.dojoblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 12:58 PM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
}
