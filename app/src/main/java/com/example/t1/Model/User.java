package com.example.t1.Model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class User extends RealmObject {
    @Required
    private String login;
    @Required
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}