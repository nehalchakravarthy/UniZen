package com.unizen.app;

import java.util.ArrayList;
import java.util.List;

public class User {

    /**
     * User model to store data in database
     **/

    String userId;

    public User() {
        // Empty constructor
    }

    public User(String userId) {
        // Constructor
        this.userId = userId;
    }

    public String getUserId() {
        // Returns userId
        return userId;
    }

    public void setUserId(String userId) {
        // Sets userId
        this.userId = userId;
    }
}
