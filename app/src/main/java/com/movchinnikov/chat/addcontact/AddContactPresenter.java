package com.movchinnikov.chat.addcontact;

import com.movchinnikov.chat.addcontact.events.AddContactEvent;

public interface AddContactPresenter {
    void onShow();

    void onDestroy();

    void addContact(String email);

    void onEventMainThread(AddContactEvent event);
}

