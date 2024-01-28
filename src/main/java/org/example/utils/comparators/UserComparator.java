package org.example.utils.comparators;

import org.example.model.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        int roleComparison = user1.getRole().compareTo(user2.getRole());

        if (roleComparison != 0)
            return roleComparison;

        return user1.getCredential().getLogin().compareTo(user2.getCredential().getLogin());
    }
}