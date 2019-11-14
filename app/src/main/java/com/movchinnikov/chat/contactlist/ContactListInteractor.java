package com.movchinnikov.chat.contactlist;


public interface ContactListInteractor {
    void subscribeForContactEvents();

    void unSubscribeForContactEvents();

    void destroyContactListListener();

    void removeContact(String email);
}
