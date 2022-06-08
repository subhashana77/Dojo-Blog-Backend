package com.arpico.dojoblog.service.impl;

import com.arpico.dojoblog.dto.AuthorDto;
import com.arpico.dojoblog.dto.PostDto;
import com.arpico.dojoblog.dto.ResponseDto;
import com.arpico.dojoblog.model.Admin;
import com.arpico.dojoblog.model.Author;
import com.arpico.dojoblog.model.Post;
import com.arpico.dojoblog.repo.AuthorRepo;
import com.arpico.dojoblog.service.AuthorService;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author dilshan.r
 * @created 6/7/2022 - 1:29 PM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public ResponseDto newAuthor(AuthorDto authorDto) {
        String name = authorDto.getName();
        String username = authorDto.getUsername();
        String password = authorDto.getPassword();
        String email = authorDto.getEmail();
        String telephone = authorDto.getTelephone();

        try {
            if (name.isEmpty()) {
                return new ResponseDto(
                        false,
                        "Author name is required"
                );
            } else if (username.isEmpty()) {
                return new ResponseDto(
                        false,
                        "Username name is required"
                );
            } else if (password.isEmpty()) {
                return new ResponseDto(
                        false,
                        "Password is required"
                );
            } else if (email.isEmpty()) {
                return new ResponseDto(
                        false,
                        "Email is required"
                );
            } else if (telephone.isEmpty()) {
                return new ResponseDto(
                        false,
                        "Telephone number is required"
                );
            } else {
                try {
                    String authorByUsername = authorRepo.getAuthorNameByUsername(username);
                    String authorByEmail = authorRepo.getAuthorNameByEmail(email);
                    String authorByTelephone = authorRepo.getAuthorNameByTelephone(telephone);

                    if (username.equalsIgnoreCase(authorByUsername)) {
                        return new ResponseDto(
                                false,
                                "Username already in used!"
                        );
                    }  else if (email.equalsIgnoreCase(authorByEmail)) {
                        return new ResponseDto(
                                false,
                                "Email already in used!"
                        );
                    } else if (telephone.equalsIgnoreCase(authorByTelephone)) {
                        return new ResponseDto(
                                false,
                                "Telephone number already in used!"
                        );
                    } else {
                        Author author = new Author();

                        author.setName(name);
                        author.setTelephone(telephone);
                        author.setEmail(email);
                        author.setUsername(username);
                        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                        String encodedPassword = passwordEncoder.encode(password);
                        author.setPassword(encodedPassword);

                        authorRepo.save(author);

                        AuthorDto savedAuthorDto = new AuthorDto();

                        savedAuthorDto.setId(author.getId());
                        savedAuthorDto.setName(author.getName());
                        savedAuthorDto.setEmail(author.getEmail());
                        savedAuthorDto.setPassword(author.getPassword());
                        savedAuthorDto.setTelephone(author.getTelephone());
                        savedAuthorDto.setUsername(author.getUsername());

                        return new ResponseDto(
                                true,
                                name + "has added!",
                                savedAuthorDto
                        );
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return new ResponseDto(
                            false,
                            "Author Added fail | " + exception
                    );
                }
            }
        } catch (Exception exception) {
            return new ResponseDto(
                    false,
                    "Something went wrong! | " + exception
            );
        }
    }

    @Override
    public ResponseDto deleteAuthor(Long id) {
        try {
            Optional<Author> authorById = authorRepo.findById(id);

            if (authorById.isPresent()) {
                try {
                    Author author = authorById.get();
                    authorRepo.delete(author);

                    return new ResponseDto(
                            true,
                            author.getName() + " has deleted!"
                    );
                } catch (Exception exception) {
                    return new ResponseDto(
                            false,
                            "Author delete fail | " + exception
                    );
                }
            } else {
                return new ResponseDto(
                        false,
                        "Author not found!"
                );
            }
        } catch (Exception exception) {
            return new ResponseDto(
                    false,
                    "Something went wrong | " + exception
            );
        }
    }

    @Override
    public ResponseDto updateAuthor(AuthorDto authorDto, Long id) {
        try {
            Optional<Author> authorById = authorRepo.findById(id);

            if (authorById.isPresent()) {
                String name = authorDto.getName();
                String telephone = authorDto.getTelephone();
                String email = authorDto.getEmail();
                String username = authorDto.getUsername();
                String password = authorDto.getPassword();

                if (name.isEmpty()) {
                    return new ResponseDto(
                            false,
                            "Name is required"
                    );
                } else if (telephone.isEmpty()) {
                    return new ResponseDto(
                            false,
                            "Telephone is required"
                    );
                } else if (email.isEmpty()) {
                    return new ResponseDto(
                            false,
                            "Email is required!"
                    );
                } else if (username.isEmpty()) {
                    return new ResponseDto(
                            false,
                            "username is required!"
                    );
                } else if (password.isEmpty()) {
                    return new ResponseDto(
                            false,
                            "Password is required!"
                    );
                } else {
                    try {
                        Author author = authorById.get();

                        author.setName(name);
                        author.setTelephone(telephone);
                        author.setEmail(email);
                        author.setUsername(username);
                        author.setPassword(password);

                        authorRepo.save(author);

                        AuthorDto updatedAuthor = new AuthorDto();

                        updatedAuthor.setId(author.getId());
                        updatedAuthor.setName(author.getName());
                        updatedAuthor.setTelephone(author.getTelephone());
                        updatedAuthor.setEmail(author.getEmail());
                        updatedAuthor.setUsername(author.getUsername());
                        updatedAuthor.setPassword(author.getPassword());

                        return new ResponseDto(
                                true,
                                name + " has updated!",
                                updatedAuthor
                        );
                    } catch (Exception exception) {
                        return new ResponseDto(
                                false,
                                "Author update fail!"
                        );
                    }
                }
            } else {
                return new ResponseDto(
                        false,
                        "Author not found!"
                );
            }
        } catch (Exception exception) {
            return new ResponseDto(
                    false,
                    "Something went wrong!"
            );
        }
    }

    @Override
    public ResponseDto getAllOwnPosts(Long id) {
        try {
            Optional<Author> authorById = authorRepo.findById(id);

            if (authorById.isPresent()) {
                List<Post> allPostByAuthor = authorRepo.getAllPostByAuthor(id);
                List<PostDto> postList = new ArrayList<>();

                for (Post post : allPostByAuthor) {
                    PostDto postDto = new PostDto();

                    postDto.setId(post.getId());
                    postDto.setTitle(post.getTitle());
                    postDto.setThumbnail(post.getThumbnail());
                    postDto.setPost(post.getPost());
                    postDto.setStatus(post.getStatus());
                    postDto.setPublishedDate(post.getPublishedDate());
                    postDto.setAuthorId(post.getAuthor().getId());

                    postList.add(postDto);
                }
                return new ResponseDto(
                        true,
                        "Fetch your all post",
                        postList
                );
            } else {
                return new ResponseDto(
                        false,
                        "Author not found!"
                );
            }
        } catch (Exception exception) {
            return new ResponseDto(
                    false,
                    "Something went wrong! | " + exception
            );
        }
    }

    @Override
    public ResponseDto findOwnPost(Long id, String keyword) {
        try {
            Optional<Author> authorById = authorRepo.findById(id);

            if (authorById.isPresent()) {
                List<Post> ownPost = authorRepo.findOwnPost(id, keyword);
                List<PostDto> postList = new ArrayList<>();

                if (ownPost.isEmpty()) {
                    return new ResponseDto(
                            false,
                            "Cannot found the post for your keyword!"
                    );
                } else {
                    for (Post post : ownPost) {
                        PostDto postDto = new PostDto();

                        postDto.setId(post.getId());
                        postDto.setTitle(post.getTitle());
                        postDto.setThumbnail(post.getThumbnail());
                        postDto.setPost(post.getPost());
                        postDto.setStatus(post.getStatus());
                        postDto.setPublishedDate(post.getPublishedDate());
                        postDto.setAuthorId(post.getAuthor().getId());

                        postList.add(postDto);
                    }
                    return new ResponseDto(
                            true,
                            "Found your all required posts",
                            postList
                    );
                }
            } else {
                return new ResponseDto(
                        false,
                        "Author not found!"
                );
            }
        } catch (Exception exception) {
            return new ResponseDto(
                    false,
                    "Something went wrong! | " + exception
            );
        }
    }

    @Override
    public ResponseDto getApprovedPosts(Long id, String status) {
        try {
            Optional<Author> authorById = authorRepo.findById(id);

            if (authorById.isPresent()) {
                List<Post> approvedPosts = authorRepo.getApprovedPosts(id, status);
                List<PostDto> postList = new ArrayList<>();

                if (approvedPosts.isEmpty()) {
                    return new ResponseDto(
                            false,
                            "Cannot find any " + status + " posts"
                    );
                } else {
                    for (Post post : approvedPosts) {
                        PostDto postDto = new PostDto();

                        postDto.setId(post.getId());
                        postDto.setTitle(post.getTitle());
                        postDto.setThumbnail(post.getThumbnail());
                        postDto.setPost(post.getPost());
                        postDto.setStatus(post.getStatus());
                        postDto.setPublishedDate(post.getPublishedDate());
                        postDto.setAuthorId(post.getAuthor().getId());

                        postList.add(postDto);
                    }

                    return new ResponseDto(
                            true,
                            "All " + status + " posts are found",
                            postList
                    );
                }
            } else {
                return new ResponseDto(
                        false,
                        "Author not found!"
                );
            }
        } catch (Exception exception) {
            return new ResponseDto(
                    false,
                    "Something went wrong! | " + exception
            );
        }
    }
}
