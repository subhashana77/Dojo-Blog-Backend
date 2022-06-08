package com.arpico.dojoblog.service;

import com.arpico.dojoblog.dto.PostDto;
import com.arpico.dojoblog.dto.ResponseDto;
import org.springframework.stereotype.Service;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 12:56 PM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

public interface PostService {
    ResponseDto approvePost(PostDto postDto, Long id);
    ResponseDto newPost(PostDto postDto);
    ResponseDto deletePost(Long id);
    ResponseDto editPost(PostDto postDto, Long id);
    ResponseDto getAllPost();
}
