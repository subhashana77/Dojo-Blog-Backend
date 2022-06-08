package com.arpico.dojoblog.service;

import com.arpico.dojoblog.dto.AuthorDto;
import com.arpico.dojoblog.dto.ResponseDto;

/**
 * @author dilshan.r
 * @created 6/7/2022 - 1:27 PM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

public interface AuthorService {
    ResponseDto newAuthor(AuthorDto authorDto);
    ResponseDto deleteAuthor(Long id);
    ResponseDto updateAuthor(AuthorDto authorDto, Long id);
    ResponseDto getAllOwnPosts(Long id);
    ResponseDto findOwnPost(Long id, String keyword);
    ResponseDto getApprovedPosts(Long id, String status);
}
