package com.example.clihis.Model;

public class User {
    private String id;
    private String username;
    private String imageURL;
    private String dr;

    public User(String id, String username, String imageURL, String dr) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.dr = dr;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
