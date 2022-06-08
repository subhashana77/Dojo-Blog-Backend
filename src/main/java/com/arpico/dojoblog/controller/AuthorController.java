package com.arpico.dojoblog.controller;

import com.arpico.dojoblog.dto.AuthorDto;
import com.arpico.dojoblog.dto.PostDto;
import com.arpico.dojoblog.dto.ResponseDto;
import com.arpico.dojoblog.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dilshan.r
 * @created 6/7/2022 - 1:26 PM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/author/save")
    public ResponseDto saveAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.newAuthor(authorDto);
    }

    @DeleteMapping("/author/delete")
    public ResponseDto deleteAuthor(@RequestParam("id") Long id) {
        return authorService.deleteAuthor(id);
    }

    @PutMapping("/author/edit")
    public ResponseDto editPost(@RequestBody AuthorDto authorDto, @RequestParam("id") Long id) {
        return authorService.updateAuthor(authorDto, id);
    }

    @GetMapping("/author/get-own-post")
    public ResponseDto getOwnPost(@RequestParam("id") Long id) {
        return authorService.getAllOwnPosts(id);
    }

    @GetMapping("/author/find-own-post")
    public ResponseDto findOwnPost(@RequestParam("id") Long id, @RequestParam("keyword") String keyword) {
        return authorService.findOwnPost(id, keyword);
    }

    @GetMapping("/author/status")
    public ResponseDto getApprovedPosts(@RequestParam("id") Long id, @RequestParam("status") String status) {
        return authorService.getApprovedPosts(id, status);
    }
}
