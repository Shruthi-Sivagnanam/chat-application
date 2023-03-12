package com.example.talkonline;

public class User {
    private String username;
    private String email;
    private String profilePicture;

    public User(){

    }
    public User(String username,String email,String profilePicture){
        this.username = username;
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePicture(){
        return  profilePicture;
    }
}
