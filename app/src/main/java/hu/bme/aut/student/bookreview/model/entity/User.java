package hu.bme.aut.student.bookreview.model.entity;

/**
 * Registration object.
 * Created by Daniel Zolnai on 2017-04-30.
 */
public class User {
    private String _username;

    public User(String username) {
        _username = username;
    }

    public String getUsername() {
        return _username;
    }
}
