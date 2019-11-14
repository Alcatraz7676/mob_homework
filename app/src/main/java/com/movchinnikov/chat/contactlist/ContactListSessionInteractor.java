package com.movchinnikov.chat.contactlist;


public interface ContactListSessionInteractor {
    void signOff();

    String getCurrentUserEmail();

    void changeConnectionStatus(boolean online);
}
