package com.arpico.dojoblog.repo;

import com.arpico.dojoblog.model.Author;
import com.arpico.dojoblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 1:24 PM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    @Query(value = "SELECT email FROM author WHERE email = ?1", nativeQuery = true)
    public String getAuthorNameByEmail(String email);

    @Query(value = "SELECT username FROM author WHERE username = ?1", nativeQuery = true)
    public String getAuthorNameByUsername(String username);

    @Query(value = "SELECT telephone FROM author WHERE telephone = ?1", nativeQuery = true)
    public String getAuthorNameByTelephone(String telephone);

    @Query("FROM Post p WHERE p.author.id = :id")
    public List<Post> getAllPostByAuthor(@Param("id") Long id);

    @Query("FROM Post p WHERE p.author.id = :id AND p.title LIKE %:keyword% OR p.publishedDate LIKE %:keyword%")
    public List<Post> findOwnPost(@Param("id") Long id,
                                  @Param("keyword") String keyword);

    @Query("FROM Post p WHERE p.author.id = :id AND p.status = :status")
    public List<Post> getApprovedPosts(@Param("id") Long id,
                                       @Param("status") String status);
}
