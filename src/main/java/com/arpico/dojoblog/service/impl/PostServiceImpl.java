package com.arpico.dojoblog.service.impl;

import com.arpico.dojoblog.dto.PostDto;
import com.arpico.dojoblog.dto.ResponseDto;
import com.arpico.dojoblog.model.Post;
import com.arpico.dojoblog.repo.AuthorRepo;
import com.arpico.dojoblog.repo.PostRepo;
import com.arpico.dojoblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 12:57 PM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public ResponseDto approvePost(PostDto postDto, Long postId) {

        Optional<Post> postById = postRepo.findById(postDto.getId());

        if (!postById.isPresent()) {
            return new ResponseDto(
                    false,
                    "Post not found!"
            );
        } else {
            try {
                Long id = postDto.getId();
                String title = postDto.getTitle();
                String thumbnail = postDto.getThumbnail();
                String post = postDto.getPost();
                String publishedDate = postDto.getPublishedDate();
                Long authorId = postDto.getAuthorId();
                String status = postDto.getStatus();

                Post posts = postById.get();

                posts.setTitle(title);
                posts.setThumbnail(thumbnail);
                posts.setPost(post);
                posts.setPublishedDate(publishedDate);
                posts.setAuthor(authorRepo.getById(authorId));
                posts.setStatus(status);

                postRepo.save(posts);

                PostDto updatePostDto = new PostDto();

                updatePostDto.setId(id);
                updatePostDto.setTitle(title);
                updatePostDto.setThumbnail(thumbnail);
                updatePostDto.setPost(post);
                updatePostDto.setPublishedDate(publishedDate);
                updatePostDto.setAuthorId(authorId);
                updatePostDto.setStatus(status);

                return new ResponseDto(
                        true,
                        "Post Post Approved!",
                        updatePostDto
                );
            } catch (Exception exception) {
                return new ResponseDto(
                        false,
                        "Something went wrong : | " + exception
                );
            }
        }
    }

    @Override
    public ResponseDto newPost(PostDto postDto) {
        try {
            Date date = new Date();
            String strDateFormat = "hh:mm:ss a";
            DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

            String title = postDto.getTitle();
            String thumbnail = postDto.getThumbnail();
            String blogPost = postDto.getPost();
            String publishedDate = dateFormat.format(date);
            Long authorId = postDto.getAuthorId();
            String status = "Pending";

            if (title.isEmpty()) {
                return new ResponseDto(
                        false,
                        "Title is required"
                );
            } else if (blogPost.isEmpty()) {
                return new ResponseDto(
                        false,
                        "Post is required"
                );
            } else {
                try {
                    Post post = new Post();

                    post.setTitle(title);
                    post.setThumbnail(thumbnail);
                    post.setPost(blogPost);
                    post.setPublishedDate(publishedDate);
                    post.setAuthor(authorRepo.getById(authorId));
                    post.setStatus(status);

                    postRepo.save(post);

                    PostDto savedPostDto = new PostDto();

                    savedPostDto.setTitle(title);
                    savedPostDto.setThumbnail(thumbnail);
                    savedPostDto.setPost(blogPost);
                    savedPostDto.setPublishedDate(publishedDate);
                    savedPostDto.setAuthorId(authorId);
                    savedPostDto.setStatus(status);

                    return new ResponseDto(
                            true,
                            "Post " + title + " is waiting to admin approval",
                            savedPostDto
                    );
                } catch (Exception exception) {
                    return new ResponseDto(
                            false,
                            "Post upload fail! | " +exception
                    );
                }
            }
        } catch (Exception exception) {
            return new ResponseDto(
                    false,
                    "Something went wrong : | " + exception
            );
        }
    }

    @Override
    public ResponseDto deletePost(Long id) {
        try {
            Optional<Post> postById = postRepo.findById(id);

            if (postById.isPresent()) {
                try {
                    Post post = postById.get();
                    postRepo.delete(post);

                    return new ResponseDto(
                            true,
                            post.getTitle() + " deleted!"
                    );
                } catch (Exception exception) {
                    return new ResponseDto(
                            false,
                            "post delete fail! | " + exception
                    );
                }
            } else {
                return new ResponseDto(
                        false,
                        "Cannot find the post!"
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
    public ResponseDto editPost(PostDto postDto, Long id) {
        try {
            Optional<Post> postById = postRepo.findById(id);

            if (!postById.isPresent()) {
                return new ResponseDto(
                        false,
                        "Post not found!"
                );
            } else {
                try {
                    String title = postDto.getTitle();
                    String thumbnail = postDto.getThumbnail();
                    String post = postDto.getPost();
                    String publishedDate = postDto.getPublishedDate();

                    if (title.isEmpty()) {
                        return new ResponseDto(
                                false,
                                "Title is required!"
                        );
                    } else if (post.isEmpty()) {
                        return new ResponseDto(
                                false,
                                "Post is required!"
                        );
                    } else if (publishedDate.isEmpty()) {
                        return new ResponseDto(
                                false,
                                "Published date is required!"
                        );
                    } else {
                        Post existPost = postById.get();

                        existPost.setTitle(title);
                        existPost.setThumbnail(thumbnail);
                        existPost.setPost(post);
                        existPost.setPublishedDate(publishedDate);
                        existPost.setStatus(existPost.getStatus());
                        existPost.setAuthor(existPost.getAuthor());

                        postRepo.save(existPost);

                        PostDto updatedPost = new PostDto();

                        updatedPost.setId(existPost.getId());
                        updatedPost.setTitle(existPost.getTitle());
                        updatedPost.setThumbnail(existPost.getThumbnail());
                        updatedPost.setPost(existPost.getPost());
                        updatedPost.setPublishedDate(existPost.getPublishedDate());
                        updatedPost.setStatus(existPost.getStatus());
                        updatedPost.setAuthorId(existPost.getAuthor().getId());

                        return new ResponseDto(
                                true,
                                title + " has updated!",
                                updatedPost
                        );
                    }
                } catch (Exception exception) {
                    return new ResponseDto(
                            false,
                            "Post update fail! | " + exception
                    );
                }
            }
        } catch (Exception exception) {
            return new ResponseDto(
                    false,
                    "Something went wrong!"
            );
        }
    }

    @Override
    public ResponseDto getAllPost() {
        try {
            List<Post> allPosts = postRepo.findAll();
            List<PostDto> postList = new ArrayList<>();

            if (allPosts.isEmpty()) {
                return new ResponseDto(
                        false,
                        "No any post to read!"
                );
            } else {
                for (Post post : allPosts) {
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
                        "Find all posts!",
                        postList
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
