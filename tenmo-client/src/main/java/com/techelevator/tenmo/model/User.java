package com.techelevator.tenmo.model;

public class User {

    private Integer user_id;
    private String username;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof User) {
            User otherUser = (User) other;
            return otherUser.getUser_id().equals(user_id)
                    && otherUser.getUsername().equals(username);
        } else {
            return false;
        }
    }
}
