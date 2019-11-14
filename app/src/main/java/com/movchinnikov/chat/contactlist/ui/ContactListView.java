package com.movchinnikov.chat.contactlist.ui;

import com.movchinnikov.chat.entities.User;

public interface ContactListView {
    void onContactAdded(User user);

    void onContactChanged(User user);

    void onContactRemoved(User user);
}
