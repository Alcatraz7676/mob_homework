package com.movchinnikov.chat.contactlist;

import com.movchinnikov.chat.contactlist.events.ContactListEvent;

public interface ContactListPresenter {
    void onPause();

    void onResume();

    void onCreate();

    void onDestroy();

    void signOff();

    String getCurrentUserEmail();

    void removeContact(String email);

    void onEventMainThread(ContactListEvent event);
}
