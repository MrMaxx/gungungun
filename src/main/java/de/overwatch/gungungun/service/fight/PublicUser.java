package de.overwatch.gungungun.service.fight;

import de.overwatch.gungungun.domain.User;

public class PublicUser {

    private Long id;
    private String userName;

    public PublicUser(User user) {
        this.id = user.getId();
        this.userName = user.getLogin();
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
