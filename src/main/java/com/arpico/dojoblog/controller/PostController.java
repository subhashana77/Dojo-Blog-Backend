package com.arpico.dojoblog.controller;

import com.arpico.dojoblog.dto.PostDto;
import com.arpico.dojoblog.dto.ResponseDto;
import com.arpico.dojoblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 2:49 PM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/post/save")
    public ResponseDto savePost(@RequestBody PostDto postDto) {
        return postService.newPost(postDto);
    }

    @DeleteMapping("/post/delete")
    public ResponseDto deletePost(@RequestParam("id") Long id) {
        return postService.deletePost(id);
    }

    @PutMapping("/post/edit")
    public ResponseDto editPost(@RequestBody PostDto postDto, @RequestParam("id") Long id) {
        return postService.editPost(postDto, id);
    }

    @GetMapping("/post/all")
    public ResponseDto getAllPost() {
        return postService.getAllPost();
    }
}
