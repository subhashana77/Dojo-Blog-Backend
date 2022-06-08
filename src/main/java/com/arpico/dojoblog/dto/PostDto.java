package com.arpico.dojoblog.dto;

/**
 * @author dilshan.r
 * @created 6/1/2022 - 9:47 AM
 * @project dojo-blog
 * @ide IntelliJ IDEA
 */
public class PostDto {
    private Long id;
    private String title;
    private String post;
    private String thumbnail;
    private String publishedDate;
    private String status;
    private Long authorId;

    public PostDto() {
    }

    public PostDto(Long id, String title, String post, String thumbnail, String publishedDate, String status, Long authorId) {
        this.id = id;
        this.title = title;
        this.post = post;
        this.thumbnail = thumbnail;
        this.publishedDate = publishedDate;
        this.status = status;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
