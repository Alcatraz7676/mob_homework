package com.movchinnikov.chat.contactlist;


public class ContactListSessionInteractorImpl implements ContactListSessionInteractor {
    ContactListRepositoryImpl contactListRepository;

    public ContactListSessionInteractorImpl() {
        this.contactListRepository = new ContactListRepositoryImpl();
    }

    @Override
    public void signOff() {
        contactListRepository.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return contactListRepository.getCurrentEmail();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        contactListRepository.changeUserConnectionStatus(online);
    }
}
