package com.example.cilo.politiko;

/**
 * Created by cilo on 9/18/17.
 */

public class User {
    String email;
    int user_id;

    public User(){}

    public User(String email, int user_id) {
        this.email = email;
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
