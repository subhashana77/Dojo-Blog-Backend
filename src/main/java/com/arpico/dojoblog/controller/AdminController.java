package com.arpico.dojoblog.controller;

import com.arpico.dojoblog.dto.AdminDto;
import com.arpico.dojoblog.dto.PostDto;
import com.arpico.dojoblog.dto.ResponseDto;
import com.arpico.dojoblog.dto.RoleDto;
import com.arpico.dojoblog.model.Admin;
import com.arpico.dojoblog.model.Role;
import com.arpico.dojoblog.service.AdminService;
import com.arpico.dojoblog.service.PostService;
import com.arpico.dojoblog.service.RoleService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 11:06 AM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PostService postService;

    @GetMapping("/role")
    public List<RoleDto> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletResponse response, HttpServletRequest request) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Admin admin = adminService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(admin.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles", admin.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                HashMap<String, String> token = new HashMap<>();
                token.put("access_token", access_token);
                token.put("refresh_token", refresh_token);

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), token);

            } catch (Exception exception) {
                System.out.println("Error logging in : " + exception.getMessage());
                response.setHeader("Error ", exception.getMessage());

                response.setStatus(FORBIDDEN.value());
                HashMap<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            System.out.println("Refresh Token Is Missing !");
        }
    }

    @GetMapping("/admin/all")
    public List<AdminDto> getAdmin() {
        return adminService.getUser();
    }

    @PutMapping("/admin/approved")
    public ResponseDto postApproved(@RequestBody PostDto postDto, @RequestParam("id") Long id) {
        return postService.approvePost(postDto, id);
    }

    @DeleteMapping("/admin/delete")
    public ResponseDto deletePost(@RequestParam("id") Long id) {
        return postService.deletePost(id);
    }
}
