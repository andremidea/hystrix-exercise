package com.andremidea.hystrixservice.model;

public class Gists {

    private String url;
    private String description;
    private Integer comments;
    private String user;

    public Gists(String description) {
        this.description = description;
    }

    public Gists() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
